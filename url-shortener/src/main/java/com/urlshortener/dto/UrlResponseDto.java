package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Response returned after creating or querying a short URL")
public class UrlResponseDto {

    @Schema(description = "Database ID", example = "1")
    private Long id;

    @Schema(description = "The short code", example = "aB3xYz")
    private String shortCode;

    @Schema(description = "Full short URL", example = "http://localhost:8080/api/aB3xYz")
    private String shortUrl;

    @Schema(description = "Original long URL",
            example = "https://www.example.com/very/long/url")
    private String originalUrl;

    @Schema(description = "Number of times this URL was visited", example = "42")
    private Long clickCount;

    @Schema(description = "When this short URL was created")
    private LocalDateTime createdAt;

    @Schema(description = "When this short URL was last accessed")
    private LocalDateTime updatedAt;

    public UrlResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public String getShortUrl() { return shortUrl; }
    public void setShortUrl(String shortUrl) { this.shortUrl = shortUrl; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public Long getClickCount() { return clickCount; }
    public void setClickCount(Long clickCount) { this.clickCount = clickCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}