package com.urlshortener.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.urlshortener.dto.StatsDto;
import com.urlshortener.dto.UrlRequestDto;
import com.urlshortener.dto.UrlResponseDto;
import com.urlshortener.entity.UrlMapping;
import com.urlshortener.exception.ShortCodeAlreadyExistsException;
import com.urlshortener.exception.ShortCodeNotFoundException;
import com.urlshortener.repository.UrlMappingRepository;

@Service
public class UrlMappingService {

    private final UrlMappingRepository repository;

    // characters used to generate short codes
    private static final String CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String BASE_URL = "http://localhost:8080/api/";

    public UrlMappingService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    // generate random 6-char alphanumeric code
    private String generateShortCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    // generate unique code — retry if collision detected
    private String generateUniqueCode() {
        String code;
        int attempts = 0;
        do {
            code = generateShortCode();
            attempts++;
            if (attempts > 10) {
                throw new RuntimeException("Failed to generate unique code");
            }
        } while (repository.existsByShortCode(code));
        return code;
    }

    // map entity to UrlResponseDto
    private UrlResponseDto toResponseDto(UrlMapping entity) {
        UrlResponseDto dto = new UrlResponseDto();
        dto.setId(entity.getId());
        dto.setShortCode(entity.getShortCode());
        dto.setShortUrl(BASE_URL + entity.getShortCode());
        dto.setOriginalUrl(entity.getOriginalUrl());
        dto.setClickCount(entity.getClickCount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    // CREATE short URL
    @Transactional
    public UrlResponseDto createShortUrl(UrlRequestDto dto) {
        String shortCode;

        // if custom alias provided — use it
        if (dto.getCustomAlias() != null && !dto.getCustomAlias().isBlank()) {
            // check if custom alias already taken
            if (repository.existsByShortCode(dto.getCustomAlias())) {
                throw new ShortCodeAlreadyExistsException(dto.getCustomAlias());
            }
            shortCode = dto.getCustomAlias();
        } else {
            // generate random unique code
            shortCode = generateUniqueCode();
        }

        UrlMapping entity = new UrlMapping();
        entity.setOriginalUrl(dto.getOriginalUrl());
        entity.setShortCode(shortCode);
        entity.setClickCount(0L);

        UrlMapping saved = repository.save(entity);
        return toResponseDto(saved);
    }

    // RESOLVE and redirect — increment click count
    @Transactional
    public String resolveAndRedirect(String shortCode) {
        UrlMapping entity = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));

        // increment click count atomically
        entity.setClickCount(entity.getClickCount() + 1);
        repository.save(entity);

        return entity.getOriginalUrl();
    }

    // GET stats
    public StatsDto getStats(String shortCode) {
        UrlMapping entity = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));

        StatsDto dto = new StatsDto();
        dto.setShortCode(entity.getShortCode());
        dto.setOriginalUrl(entity.getOriginalUrl());
        dto.setClickCount(entity.getClickCount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLastAccessedAt(entity.getUpdatedAt());
        return dto;
    }

    // LIST all paginated
    public Page<UrlResponseDto> listAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                         .map(this::toResponseDto);
    }

    // DELETE
    @Transactional
    public void delete(String shortCode) {
        UrlMapping entity = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));
        repository.delete(entity);
    }

    // TOP 5
    public List<UrlResponseDto> getTopUrls() {
        return repository.findTop5ByOrderByClickCountDesc()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}