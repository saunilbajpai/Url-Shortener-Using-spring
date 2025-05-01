package com.example.URL.shortner.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.URL.shortner.Model.UrlMapping;
import com.example.URL.shortner.Repository.UrlMappingRepo;

@Service
public class UrlMappingService {
    @Autowired
    private UrlMappingRepo urlrepo;

    public String createShortUrl(String originalUrl,String customCode){
        String shortcode;
        if(customCode != null && !customCode.isEmpty()){
            Optional<UrlMapping> exist = urlrepo.findByShortCode(customCode);
            if(exist.isPresent()){
                throw new RuntimeException("Custom short code already exist");
            }
            shortcode= customCode;
        }
        else{
            shortcode = UUID.randomUUID().toString().substring(0, 6);
        }
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortcode);
        mapping.setCreatedAt(LocalDateTime.now());

        urlrepo.save(mapping);

        return "Shortened URL: http://localhost:8080/" + shortcode;
    }

    public String getOriginalUrlByShortCode(String shortCode) {
        Optional<UrlMapping> urlMapping = urlrepo.findByShortCode(shortCode);
        if (urlMapping.isPresent()) {
            return urlMapping.get().getOriginalUrl(); // Return the original URL if found
        } else {
            throw new RuntimeException("Short code not found");
        }
    }
    
    public List<UrlMapping> getAllMappings() {
        return urlrepo.findAll();
    }
}
