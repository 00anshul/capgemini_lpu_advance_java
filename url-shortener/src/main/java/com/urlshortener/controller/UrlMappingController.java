package com.urlshortener.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.urlshortener.dto.StatsDto;
import com.urlshortener.dto.UrlRequestDto;
import com.urlshortener.dto.UrlResponseDto;
import com.urlshortener.service.UrlMappingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "URL Shortener", description = "Endpoints for shortening and managing URLs")
public class UrlMappingController {

    private final UrlMappingService service;

    public UrlMappingController(UrlMappingService service) {
        this.service = service;
    }

    // POST /api/shorten
    @PostMapping("/shorten")
    @Operation(summary = "Create a short URL",
               description = "Accepts a long URL and returns a short code")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Short URL created"),
        @ApiResponse(responseCode = "400", description = "Validation failed"),
        @ApiResponse(responseCode = "409", description = "Custom alias already taken")
    })
    public ResponseEntity<UrlResponseDto> shorten(
            @Valid @RequestBody UrlRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(service.createShortUrl(dto));
    }

    // GET /api/{shortCode} — redirect
    @GetMapping("/{shortCode}")
    @Operation(summary = "Redirect to original URL",
               description = "Resolves short code and redirects — increments click count")
    @ApiResponses({
        @ApiResponse(responseCode = "302", description = "Redirect to original URL"),
        @ApiResponse(responseCode = "404", description = "Short code not found")
    })
    public ResponseEntity<Void> redirect(
            @Parameter(description = "The short code", required = true)
            @PathVariable String shortCode) {
        String originalUrl = service.resolveAndRedirect(shortCode);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                             .headers(headers)
                             .build();
    }

    // GET /api/stats/{shortCode}
    @GetMapping("/stats/{shortCode}")
    @Operation(summary = "Get statistics for a short URL")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Stats returned"),
        @ApiResponse(responseCode = "404", description = "Short code not found")
    })
    public ResponseEntity<StatsDto> getStats(
            @Parameter(description = "The short code", required = true)
            @PathVariable String shortCode) {
        return ResponseEntity.ok(service.getStats(shortCode));
    }

    // GET /api/urls?page=0&size=10
    @GetMapping("/urls")
    @Operation(summary = "List all URLs with pagination")
    @ApiResponse(responseCode = "200", description = "Paginated list returned")
    public ResponseEntity<Page<UrlResponseDto>> listAll(
            @Parameter(description = "Page number", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.listAll(page, size));
    }

    // DELETE /api/{shortCode}
    @DeleteMapping("/{shortCode}")
    @Operation(summary = "Delete a short URL")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Short code not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "The short code to delete", required = true)
            @PathVariable String shortCode) {
        service.delete(shortCode);
        return ResponseEntity.noContent().build();
    }

    // GET /api/top
    @GetMapping("/top")
    @Operation(summary = "Get top 5 most clicked URLs")
    @ApiResponse(responseCode = "200", description = "Top 5 URLs returned")
    public ResponseEntity<List<UrlResponseDto>> getTop() {
        return ResponseEntity.ok(service.getTopUrls());
    }
}