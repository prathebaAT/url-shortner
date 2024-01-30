package com.urlshortner.urlshortner;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/url")
public class UrlShortenerController {

    private Map<String, String> urlMap = new HashMap<>();
    private static final String BASE_URL = "http://short.url/";

    @PostMapping("/shorten")
  
    public String shortenUrl(@RequestBody String longUrl) {
        String shortKey = generateShortKey();
        String shortUrl = BASE_URL + shortKey;
        urlMap.put(shortKey, longUrl);
        return shortUrl;
    }

    @GetMapping("/expand/{shortKey}")
    public RedirectView expandUrl(@PathVariable String shortKey) {
        if (urlMap.containsKey(shortKey)) {
            String longUrl = urlMap.get(shortKey);
            return new RedirectView(longUrl);
        } else {
            
            return new RedirectView("/error");
        }
    }

    private String generateShortKey() {
       
        return String.valueOf(urlMap.size() + 1);
    }
}
