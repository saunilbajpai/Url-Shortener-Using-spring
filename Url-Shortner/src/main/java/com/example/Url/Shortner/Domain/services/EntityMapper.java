package com.example.Url.Shortner.Domain.services;


import com.example.Url.Shortner.Domain.entities.ShortUrl;
import com.example.Url.Shortner.Domain.entities.User;
import com.example.Url.Shortner.Domain.models.ShortUrlDto;
import com.example.Url.Shortner.Domain.models.UserDto;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {
    public ShortUrlDto toShortUrlDto(ShortUrl shortUrl) {
        UserDto userDto = null;
        if(shortUrl.getCreatedBy() != null) {
            userDto = toUserDto(shortUrl.getCreatedBy());
        }
        return new ShortUrlDto(
                shortUrl.getId(),
                shortUrl.getShortKey(),
                shortUrl.getOriginalUrl(),
                shortUrl.getIsPrivate(),
                shortUrl.getExpiresAt(),
                userDto,
                shortUrl.getClickCount(),
                shortUrl.getCreatedAt()
        );
    }

    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
