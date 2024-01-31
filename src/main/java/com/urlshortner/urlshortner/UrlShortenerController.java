package com.urlshortner.urlshortner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/url")
public class UrlShortenerController {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    private static final String BASE_URL = "http://localhost:8080/";

    @PostMapping("/shorten")
    @ResponseBody
    public String shortenUrl(@RequestBody String longUrl) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);

        String shortKey = generateShortKey();
        urlMapping.setShortKey(shortKey);

        urlMappingRepository.save(urlMapping);

        return BASE_URL + shortKey;
    }

    @GetMapping("/expand/{shortKey}")
    public RedirectView expandUrl(@PathVariable String shortKey) {
        UrlMapping urlMapping = urlMappingRepository.findByShortKey(shortKey);

        if (urlMapping != null) {
            return new RedirectView(urlMapping.getLongUrl());
        } else {
            return new RedirectView("/error");
        }
    }

    private String generateShortKey() {
        return String.valueOf(System.currentTimeMillis());
    }
}
