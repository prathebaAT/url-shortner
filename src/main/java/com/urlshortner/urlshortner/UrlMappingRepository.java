package com.urlshortner.urlshortner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    UrlMapping findByShortKey(String shortKey);
}
