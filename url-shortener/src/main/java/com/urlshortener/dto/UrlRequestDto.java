package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request body for creating a short URL")
public class UrlRequestDto {

    @NotBlank(message = "Original URL must not be blank")
    @Schema(description = "The long URL to shorten",
            example = "https://www.example.com/very/long/url")
    private String originalUrl;

    @Size(min = 3, max = 20, message = "Custom alias must be between 3 and 20 characters")
    @Schema(description = "Optional custom alias for the short URL",
            example = "my-link")
    private String customAlias;

    public UrlRequestDto() {}

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public String getCustomAlias() { return customAlias; }
    public void setCustomAlias(String customAlias) { this.customAlias = customAlias; }
}