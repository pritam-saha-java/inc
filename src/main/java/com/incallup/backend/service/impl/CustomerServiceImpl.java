package com.incallup.backend.service.impl;


import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final LocationRepository locationRepository;


    @Override
    public Post searchByTitle(String title) throws ApplicationException{
       Optional<Post> nS = postRepository.findPostByTitle(title);
        if(nS.isEmpty()){
            throw ApplicationException.builder()
                    .title("TitleNotFound")
                    .Description("Title Not Found")
                    .status(503)
                    .build();
        }
        return nS.get();
    }

    @Override
    public List<Post> searchByCategory(String category) throws ApplicationException{
        Optional<Category> nSExists = categoryRepository.findCategoryByTitle(category);
        if(nSExists.isEmpty()){
            throw ApplicationException.builder()
                    .title("CategoryNotFound")
                    .Description("Category Not Found")
                    .status(404)
                    .build();
        }
        var categoryObj = nSExists.get();

       return categoryObj.getPosts();

    }

    @Override
    public List<Post> searchByCategoryAndLocation(String categoryString, String locationString) throws ApplicationException{
        var postsByLocationOptional = locationRepository.findLocationByDistrict(locationString);
        var categoryOptional = categoryRepository.findCategoryByTitle(categoryString);
        if(postsByLocationOptional.isEmpty()||categoryOptional.isEmpty()){
            throw ApplicationException.builder()
                    .title("NotFoundByCategoryAndLocation")
                    .Description("Not Found By Category And Location")
                    .status(503)
                    .build();
        }
        var category = categoryOptional.get();
        var locations = postsByLocationOptional.get();
        var posts = locations.getPosts();
         List<Post> postsByLocation = new LinkedList<>();
        posts.forEach( post -> {
            if(post.getCategory().equals(category))
                postsByLocation.add(post);
        });

        return new ArrayList<>(postsByLocation);
    }

    @Override
    public List<Post> searchByLocation(String location) throws ApplicationException{
        var locationOpt = locationRepository.findLocationByDistrict(location);
        if(locationOpt.isEmpty()){
            throw ApplicationException.builder()
                    .title("LocationNotFound")
                    .Description("Location Not Found")
                    .status(402)
                    .build();
        }
        var locationObj = locationOpt.get();
        return locationObj.getPosts();

    }

}
