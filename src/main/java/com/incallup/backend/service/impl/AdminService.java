package com.incallup.backend.service.impl;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.repository.SellerRepository;
import com.incallup.backend.service.AdminCommandService;
import com.incallup.backend.service.AdminQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This Service should implement both {@link com.incallup.backend.service.AdminCommandService} and {@link com.incallup.backend.service.AdminQueryService} interfaces
 **/
@Service
@RequiredArgsConstructor
public class AdminService implements AdminQueryService, AdminCommandService {

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

        var categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(categoryId)
                    .message("Other")
                    .entity("category")
                    .build();
        }
        return categoryOptional.get();
    }

    @Override
    public void blockPost(Integer postId) throws IdNotFoundException {

        var postOptional = postRepository.findById(postId);
        boolean dExist = postOptional.isEmpty();
        if(dExist){
            throw IdNotFoundException.builder().build();
        }
        var post = postOptional.get();
        post.setIsBlocked(true);

    }

    @Override
    public void blockSeller(Integer sellerId) throws IdNotFoundException {

        var sellerOptinal = sellerRepository.findById(sellerId);
        boolean aExist = sellerOptinal.isEmpty();
        if (aExist){
            throw IdNotFoundException.builder().build();
        }
        var seller = sellerOptinal.get();
        seller.setIsBlocked(true);


    }

    @Override
    public void createLocation(Location location) throws ApplicationException {
           boolean isExist = locationRepository.findById(location.getId()).isPresent();
           if(isExist){
               throw ApplicationException.builder()
                       .title("IdAlreadyExists")
                       .Description("Id already exists")
                       .status(300)
                       .build();
           }
           isExist = locationRepository.findLocationByDistrict(location.getDistrict()).isPresent();

           if(isExist){
               throw ApplicationException.builder()
                       .title("")
                       .Description("district already exists")
                       .status(300)
                       .build();
           }

           locationRepository.save(location);
    }

    @Override
    public void createCategory(Category category) throws ApplicationException {

        boolean isExist = categoryRepository.findCategoryByTitle(category.getTitle()).isPresent();

        if(isExist){
            throw ApplicationException.builder()
                    .title("CategoryExists")
                    .Description("Category Already Exists")
                    .status(300)
                    .build();
        }
        var title = category.getTitle();
        title = title.toLowerCase();
        title = title.replace(' ','-');
        category.setName(title);
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) throws ApplicationException {
        boolean aExist = categoryRepository.findById(category.getId()).isPresent();
        if (aExist){
            throw ApplicationException.builder()
                    .title("NoUpdate")
                    .Description("Update not availiable")
                    .status(300)
                    .build();
        }
        aExist = categoryRepository.findCategoryByTitle(category.getName()).isPresent();

        if(aExist){
            throw ApplicationException.builder()
                    .title("UpdateNotApplicable")
                    .Description("Update not applicable")
                    .status(300)
                    .build();
        }

        categoryRepository.save(category);
    }

    @Override
    public void updateLocation(Location location) throws ApplicationException {
        boolean bExists = locationRepository.findById(location.getId()).isPresent();
        if(bExists){
            throw ApplicationException.builder()
                    .title("NoLocationUpdate")
                    .Description("No update required in location")
                    .status(300)
                    .build();
        }

        bExists = locationRepository.findLocationByDistrict(location.getDistrict()).isPresent();

        if(bExists){
            throw ApplicationException.builder()
                    .title("NoLocationNow")
                    .Description("No location update for now")
                    .status(300)
                    .build();
        }

        locationRepository.save(location);
    }
}
