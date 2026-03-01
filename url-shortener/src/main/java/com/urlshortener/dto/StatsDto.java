package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Statistics for a short URL")
public class StatsDto {

    @Schema(description = "The short code", example = "aB3xYz")
    private String shortCode;

    @Schema(description = "Original long URL")
    private String originalUrl;

    @Schema(description = "Total number of clicks", example = "42")
    private Long clickCount;

    @Schema(description = "When this short URL was created")
    private LocalDateTime createdAt;

    @Schema(description = "When this short URL was last accessed")
    private LocalDateTime lastAccessedAt;

    public StatsDto() {}

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public Long getClickCount() { return clickCount; }
    public void setClickCount(Long clickCount) { this.clickCount = clickCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastAccessedAt() { return lastAccessedAt; }
    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }
}