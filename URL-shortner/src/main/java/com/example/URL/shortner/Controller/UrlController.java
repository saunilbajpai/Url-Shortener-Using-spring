package com.example.URL.shortner.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.*;
import com.example.URL.shortner.Data.Urlchangedto;
import com.example.URL.shortner.Model.UrlMapping;
import com.example.URL.shortner.Service.UrlMappingService;

@RestController
public class UrlController {

    @Autowired
    private UrlMappingService urlservice;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody Urlchangedto Dto) {
        String org = Dto.getOriginalUrl();
        String custom = Dto.getCustomCode();
        return urlservice.createShortUrl(org, custom);
    }

    @GetMapping("/getOriginal/{shortCode}")
    public ResponseEntity<String> getOriginal(@PathVariable String shortCode) {
        try {
            return ResponseEntity.ok(urlservice.getOriginalUrlByShortCode(shortCode));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No original URL found for the given short code.");
        }
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<UrlMapping>> getAllMappings() {
        List<UrlMapping> urlMappings = urlservice.getAllMappings();
        if (urlMappings.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content if no entries found
        }
        return ResponseEntity.ok(urlMappings); // 200 OK with URL mappings
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        try {
            String originalUrl = urlservice.getOriginalUrlByShortCode(shortCode);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(originalUrl));
            return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 redirect
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
