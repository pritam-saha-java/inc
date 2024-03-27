package com.incallup.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class SiteMapService {

    public  String categorySiteMap(){
        StringBuilder stringBuilder = new StringBuilder("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\">\n");

        stringBuilder.append("</urlset>");

        return stringBuilder.toString();
    };
    public abstract String locationSiteMap();
    public abstract String postSiteMap();
}
