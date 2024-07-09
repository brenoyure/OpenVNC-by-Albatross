package br.albatross.open.vnc.releases.services;

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

import br.albatross.open.vnc.releases.model.Release;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

/**
 * 
 * Implementação, ainda em teste, do Serviço de Releases
 * 
 * @author Breno.Brito
 */
public class ReleasesServiceGithubImplementation implements ReleasesService {

    @Override
    public Optional<Release> getTheLatestStable() {

        try {

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

                return releases
                        .stream()
                        .filter(r -> r.getBranch().equalsIgnoreCase("main"))
                        .sorted((r1, r2) -> r2.getPublishedAt().compareTo(r1.getPublishedAt()))
                        .findFirst();

            }


        } catch (Exception e) { throw new RuntimeException(e); }

    }

    @Override
    public Optional<Release> getTheLastest() {

        try {

            HttpRequest httpRequest = HttpRequest
                    .newBuilder(URI.create("https://api.github.com/repos/brenoyure/OpenVNC-by-Albatross/releases/latest"))
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            try (JsonReader jsonReader = Json.createReader(new StringReader(httpResponse.body()))) {
                JsonObject releaseJsonObject = jsonReader.readObject();
                long id = releaseJsonObject.getInt("id");
                String name = releaseJsonObject.getString("name");
                String url = releaseJsonObject.getString("html_url");
                String branch = releaseJsonObject.getString("target_commitish");
                String description = releaseJsonObject.getString("body");
                LocalDateTime publishedAt = LocalDateTime.parse(releaseJsonObject.getString("published_at"), DateTimeFormatter.ISO_DATE_TIME);
                
                Release release = new Release(id, name, url, branch, description, publishedAt);

                return Optional.of(release);

            }

        } catch (Exception e) { throw new RuntimeException(e); }

    }

    @Override
    public List<Release> findAll() {

        try {

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

                return releases;

            }

        } catch (Exception e) { throw new RuntimeException(e); }
        
    }

    @Override
    public boolean isTheCurrentReleaseUpdated() {

        try {

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

                Release release = releases
                        .stream()
                        .filter(r -> r.getBranch().equalsIgnoreCase("main"))
                        .sorted((r1, r2) -> r2.getPublishedAt().compareTo(r1.getPublishedAt()))
                        .findFirst()
                        .get();

                return release.getName().equalsIgnoreCase(GITHUB_RELEASE_NAME) && release.getBranch().equalsIgnoreCase("main");

            }


        } catch (Exception e) { throw new RuntimeException(e); }
        
    }

}
