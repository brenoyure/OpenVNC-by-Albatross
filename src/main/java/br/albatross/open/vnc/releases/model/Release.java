package br.albatross.open.vnc.releases.model;

import java.time.LocalDateTime;

import jakarta.json.JsonValue;
import jakarta.json.bind.annotation.JsonbProperty;

/**
 * 
 * Representa os dados básicos de um Release na API do github.
 * 
 * @author breno.brito
 */
public class Release implements JsonValue {

    @JsonbProperty("id")
    private long id;

    @JsonbProperty("name")
    private String name;

    @JsonbProperty("html_url")
    private String url;

    @JsonbProperty("target_commitish")
    private String branch;

    @JsonbProperty("body")
    private String description;

    @JsonbProperty("published_at")
    private LocalDateTime publishedAt;

    public Release() {

    }

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

    @Override
    public ValueType getValueType() {
        return ValueType.OBJECT;
    }

}
