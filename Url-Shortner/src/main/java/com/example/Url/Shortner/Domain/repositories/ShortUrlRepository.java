package com.example.Url.Shortner.Domain.repositories;

import com.example.Url.Shortner.Domain.entities.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    @Query("select su from ShortUrl su left join fetch su.createdBy where su.isPrivate = false order by su.createdAt desc")
    Page<ShortUrl> findPublicShortUrls(Pageable pageable);
    boolean existsByShortKey(String shortKey);
    Optional<ShortUrl> findByShortKey(String shortKey);
}
