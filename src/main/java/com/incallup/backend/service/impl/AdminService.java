package com.incallup.backend.service.impl;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.repository.SellerRepository;
import com.incallup.backend.service.AdminQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This Service should implement both {@link com.incallup.backend.service.AdminCommandService} and {@link com.incallup.backend.service.AdminQueryService} interfaces
 * */
@Service
@RequiredArgsConstructor
public class AdminService implements AdminQueryService {

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    private final PostRepository postRepository;
    @Override
    public List<Seller> listSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public List<Post> listPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Location> listLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Integer locationId) throws IdNotFoundException {

        var locationOptional = locationRepository.findById(locationId);
        if(locationOptional.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(locationId)
                    .message("Location Not Found")
                    .entity("lcoation")
                    .build();
        }
        return locationOptional.get();
    }

    @Override
    public Category getCategoryById(Integer categoryId) throws IdNotFoundException {

        var categoryMust = categoryRepository.findById(categoryId);
        if(categoryMust.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(categoryId)
                    .message("Others")
                    .entity("category")
                    .build();
        }
        return categoryMust.get();
    }
}
