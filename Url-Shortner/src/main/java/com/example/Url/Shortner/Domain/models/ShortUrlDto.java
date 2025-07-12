package com.example.Url.Shortner.Domain.models;

import java.io.Serializable;
import java.time.Instant;

// link for shorturl Dto for
public record ShortUrlDto(Long id, String shortKey, String originalUrl,
                          Boolean isPrivate, Instant expiresAt,
                          UserDto createdBy, Long clickCount,
                          Instant createdAt) implements Serializable {
}