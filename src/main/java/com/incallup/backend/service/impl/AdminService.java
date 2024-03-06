package com.incallup.backend.service.impl;

import com.incallup.backend.configuration.WHMService;
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
import com.jcraft.jsch.SftpException;
import lombok.RequiredArgsConstructor;
import net.schmizz.sshj.sftp.SFTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                    .entity("location")
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
    public List<String> listStates() {
        Set<String> states = new HashSet<>();
        var locations = locationRepository.findAll();
        locations.forEach(location -> {
            var contains = states.contains(location.getState());
            if(!contains){
                states.add(location.getState());
            }
        });
        return new ArrayList<>(states);
    }

    @Override
    public void blockPost(Integer postId) throws IdNotFoundException {

        var postOptional = postRepository.findById(postId);
        boolean dExist = postOptional.isEmpty();
        if(dExist){
            throw IdNotFoundException.builder()
                    .id(postId)
                    .entity("Post")
                    .message("corresponding post does not exist")
                    .build();
        }
        var post = postOptional.get();
        post.setIsBlocked(true);

    }

    @Override
    public void blockSeller(Integer sellerId) throws IdNotFoundException {

        var sellerOptional = sellerRepository.findById(sellerId);
        boolean aExist = sellerOptional.isEmpty();
        if (aExist){
            throw IdNotFoundException.builder()
                    .id(sellerId)
                    .entity("Seller")
                    .message("corresponding seller does not exist")
                    .build();
        }
        var seller = sellerOptional.get();
        seller.setIsBlocked(true);


    }

    @Override
    public void createLocation(Location location) throws ApplicationException {


           if(location.getDistrict().isEmpty()){
               throw ApplicationException.builder()
                       .title("district name not found")
                       .Description("please add district name")
                       .build();
           }

           var district = location.getDistrict().toLowerCase().trim();
           location.setName(district);
           district = district.replace(" ","-");
           var state = location.getState().toLowerCase().trim();
           location.setDistrict(district);
           location.setState(state);
           locationRepository.save(location);
    }

    @Override
    public void createCategory(Category category, MultipartFile image) throws ApplicationException {

        boolean isExist = categoryRepository.findCategoryByTitle(category.getTitle()).isPresent();

        if(isExist){
            throw ApplicationException.builder()
                    .title("CategoryExists")
                    .Description("Category Already Exists")
                    .status(300)
                    .build();
        }
        var title = category.getTitle();
        if(title.isEmpty()){
            throw ApplicationException.builder()
                    .title("title is empty")
                    .Description("empty title couldn't be saved")
                    .build();
        }
        title = title.toLowerCase();
        title = title.replace(' ','-');
        category.setName(title);
        categoryRepository.save(category);
        try {

        whmService.uploadFile(image,category.getName());
        }
        catch (SftpException | IOException e){

            throw ApplicationException.builder()
                    .title("error saving image")
                    .Description(e.getMessage())
                    .build();

        }



    }

    @Autowired
    private WHMService whmService;

    @Override
    public void updateCategory(Category category) throws ApplicationException {


        try {
            categoryRepository.save(category);
        }catch (Exception e)
        {
            throw ApplicationException.builder()
                    .title("error while saving category")
                    .Description(e.getMessage())
                    .build();
        }
    }

    @Override
    public void updateLocation(Location location) throws ApplicationException {
      try {
          locationRepository.save(location);
      }
      catch (Exception e){
          throw ApplicationException.builder()
                  .title("error while saving the location")
                  .Description(e.getMessage())
                  .build();
      }
    }
}
