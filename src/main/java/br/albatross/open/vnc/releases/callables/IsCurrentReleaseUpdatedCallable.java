package br.albatross.open.vnc.releases.callables;

import static br.albatross.open.vnc.configurations.AvailableProperties.GITHUB_RELEASE_NAME;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import br.albatross.open.vnc.releases.model.Release;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class IsCurrentReleaseUpdatedCallable implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {

        HttpRequest httpRequest = HttpRequest
                .newBuilder(URI.create("https://api.github.com/repos/brenoyure/OpenVNC-by-Albatross/releases"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        
        try (JsonReader jsonReader = Json.createReader(new StringReader(httpResponse.body()))) {
            JsonArray jsonArray = jsonReader.readArray();
            List<Release> releases = new ArrayList<>();
            jsonArray.forEach(r -> {
                JsonObject releaseJsonObject = r.asJsonObject();
                long id = releaseJsonObject.getInt("id");
                String name = releaseJsonObject.getString("name");
                String url = releaseJsonObject.getString("html_url");
                String branch = releaseJsonObject.getString("target_commitish");
                String description = releaseJsonObject.getString("body");
                LocalDateTime publishedAt = LocalDateTime.parse(releaseJsonObject.getString("published_at"), DateTimeFormatter.ISO_DATE_TIME);
                releases.add(new Release(id, name, url, branch, description, publishedAt));
            });

            Optional<Release> releaseOptional = releases
                    .stream()
                    .filter(r -> r.getBranch().equalsIgnoreCase("main"))
                    .sorted((r1, r2) -> r2.getPublishedAt().compareTo(r1.getPublishedAt()))
                    .findFirst();

            return releaseOptional.get().getName().equalsIgnoreCase(GITHUB_RELEASE_NAME);

        }

    }

}
