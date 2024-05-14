package com.incallup.backend.service.impl;

import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.utility.IncallupConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public  class SiteMapService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public  String categorySiteMap(){
        StringBuilder stringBuilder = new StringBuilder("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\">\n");

        categoryRepository.findAll().forEach(category -> {

            stringBuilder.append("<url>\n");
            stringBuilder.append("<loc>");
            stringBuilder.append("https://www.incallup.com/");
            stringBuilder.append(category.getName());
            stringBuilder.append("</loc>\n");
            stringBuilder.append("<lastmod>2022-03-22</lastmod>");
            stringBuilder.append("</url>\n");

        });





        stringBuilder.append("</urlset>\n");


    return stringBuilder.toString();
    };

    @Autowired
    private LocationRepository locationRepository;
    private final PostRepository postRepository;

    public String siteMap(){


        StringBuilder content = new StringBuilder(" <sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"> \n");
        Arrays.asList(IncallupConstants.SITEMAP_PAGES.values())
                .forEach(page->{
                    content.append(" <sitemap>\n");
                    content.append("\t<loc>");
                    content.append("https://www.incallup.com/sitemap/");
                    content.append(page);
                    content.append(".xml");
                    content.append("</loc> \n");
                    content.append("  </sitemap> \n");

                });

        var locations = locationRepository.findAll();
        Set<String> states = new HashSet<>();
        locations.forEach(location -> {

            states.add(location.getState()+".xml");
        });

        states.forEach(state->{

            content.append(" <sitemap>\n");
            content.append("\t<loc>");
            content.append("https://www.incallup.com/sitemap/location/");
            content.append(state.replace(' ','_'));
            content.append("</loc> \n");
            content.append("  </sitemap> \n");

        });

        content.append(" </sitemapindex> ");

        return content.toString();
    }

    public  String locationSiteMap(String state){
        state = state.replace(".xml","");
        state = state.replace('_',' ');

        StringBuilder stringBuilder = new StringBuilder("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\">\n");
            var locations = locationRepository.findLocationsByState(state);
        categoryRepository.findAll().forEach(category -> {
                locations.forEach(location -> {

                    stringBuilder.append("<url>\n");
                    stringBuilder.append("<loc>");
                    stringBuilder.append("https://www.incallup.com/");
                    stringBuilder.append(category.getName());
                    stringBuilder.append("/");
                    stringBuilder.append(location.getDistrict());
                    stringBuilder.append("</loc>\n");
                    stringBuilder.append("<lastmod>2022-03-22</lastmod>");
                    stringBuilder.append("</url>\n");

                });




        });





        stringBuilder.append("</urlset>\n");


        return stringBuilder.toString();

    }
    public  String postSiteMap(){

        StringBuilder stringBuilder = new StringBuilder("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\">\n");

        postRepository.findAll().forEach(post -> {

            stringBuilder.append("<url>\n");
            stringBuilder.append("<loc>");
            stringBuilder.append("https://www.incallup.com/");
            stringBuilder.append(post.getCategory().getName());
            stringBuilder.append("/");
            stringBuilder.append(post.getLocation().getName());
            stringBuilder.append("/");
            stringBuilder.append(post.getName());
            stringBuilder.append("</loc>\n");
            stringBuilder.append("<lastmod>2022-03-22</lastmod>");
            stringBuilder.append("</url>\n");

        });





        stringBuilder.append("</urlset>\n");


        return stringBuilder.toString();


    }
}
