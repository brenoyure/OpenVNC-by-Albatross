package br.albatross.open.vnc.releases.services;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import br.albatross.open.vnc.releases.model.Release;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/**
 * Implementação do Serviço de Releases baseado na API do Github
 *
 * @author Breno.Brito
 */
@RequestScoped
public class ReleasesServiceGithubImplementation implements ReleasesService {

    @Override
    public Optional<Release> getTheLatestStable() {

        try {

            HttpRequest httpRequest = HttpRequest
                    .newBuilder(URI.create("https://api.github.com/repos/brenoyure/OpenVNC-by-Albatross/releases/latest"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            try (JsonReader jsonReader = Json.createReader(new StringReader(httpResponse.body()))) {

                JsonObject jsonObject = jsonReader.readObject();
                long id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                String url = jsonObject.getString("html_url");
                String branch = jsonObject.getString("target_commitish");
                String description = jsonObject.getString("body");
                LocalDateTime publishedAt = LocalDateTime.parse(jsonObject.getString("published_at"), DateTimeFormatter.ISO_DATE_TIME);

                return Optional.of(new Release(id, name, url, branch, description, publishedAt));

            }

        } catch (Exception e) { throw new RuntimeException(e); }

    }

}
