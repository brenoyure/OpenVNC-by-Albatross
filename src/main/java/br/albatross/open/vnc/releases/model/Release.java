package br.albatross.open.vnc.releases.model;

import jakarta.json.bind.annotation.JsonbProperty;

import java.time.LocalDateTime;

/**
 * Representa os dados básicos de um Release na API do github.
 *
 * @author breno.brito
 */
public class Release {

    @JsonbProperty("id")
    private final long id;

    @JsonbProperty("name")
    private final String name;

    @JsonbProperty("html_url")
    private final String url;

    @JsonbProperty("target_commitish")
    private final String branch;

    @JsonbProperty("body")
    private final String description;

    @JsonbProperty("published_at")
    private final LocalDateTime publishedAt;

    public Release(long id, String name, String url, String branch, String description, LocalDateTime publishedAt) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.branch = branch;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getBranch() {
        return branch;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }
}
