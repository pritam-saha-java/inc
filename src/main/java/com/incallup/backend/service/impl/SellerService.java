package com.incallup.backend.service.impl;


import com.incallup.backend.configuration.RemoteStorage;
import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.*;
import com.incallup.backend.repository.CategoryRepository;
import com.incallup.backend.repository.LocationRepository;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.repository.SellerRepository;
import com.incallup.backend.request.ChangePasswordRequest;
import com.incallup.backend.response.SellerAllPostResponse;
import com.incallup.backend.response.SellerUpdateProfileRequest;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import com.incallup.backend.utility.ImageUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;


/**
 * This Service should implement both {@link com.incallup.backend.service.SellerCommandService} and {@link com.incallup.backend.service.SellerQueryService} interfaces
 **/

@Service
@RequiredArgsConstructor
@Log4j2
public class SellerService implements SellerQueryService, SellerCommandService {

    @Autowired
    private final SellerRepository sellerRepository;
    @Autowired
    private final PostRepository postRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final RemoteStorage remoteStorage;


    @Override
    public Seller getSellerById(Integer sellerId) throws IdNotFoundException {

        var getSellerByIdOptional = sellerRepository.findById(sellerId);
        if(getSellerByIdOptional.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(sellerId)
                    .message("Seller Not Found")
                    .entity("seller")
                    .build();
        }
        return getSellerByIdOptional.get();
    }
    @Override
    public Post getPostById(Integer postId) throws IdNotFoundException {

        var getPostByIdOptional = postRepository.findById(postId);
        if(getPostByIdOptional.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(postId)
                    .message("Post Not Found")
                    .entity("post")
                    .build();
        }
        return getPostByIdOptional.get();
    }

    @Override
    public List<Post> getPostsBySellerId(Integer sellerId) throws IdNotFoundException {

        var sellerOptional = sellerRepository.findById(sellerId);
        if(sellerOptional.isEmpty()){

            throw IdNotFoundException.builder()
                    .id(sellerId)
                    .message("Seller Not Found")
                    .entity("seller")
                    .build();
        }
        return sellerOptional.get().getPosts();
    }

    @Override
    public boolean authenticate(String username, String password) throws LogoutException {

        var sellerOptional = sellerRepository.findSellerByUsername(username);


        if(sellerOptional.isEmpty())
            throw LogoutException.builder().title("wrong username").Description("please provide correct username").build();

        var seller = sellerOptional.get();
        if(!seller.getPassword().equals(password))
            throw LogoutException.builder().title("wrong password").Description("please enter correct password").build();

        return true;
    }

    @Override
    public Seller getSellerByUsername(String username) throws IdNotFoundException {

        var userOptional = sellerRepository.findSellerByUsername(username);

        return userOptional.orElseThrow(IdNotFoundException::new);
    }


    @Override
    public void createPost(@Valid Post post, Integer sellerId, List<MultipartFile> files) throws ApplicationException {

        var postOptional = postRepository.findPostByTitle(post.getTitle());
        if(postOptional.isPresent())
            throw ApplicationException.builder()
                    .title("title already exists")
                    .Description("please choose proper title")
                    .status(409)
                    .build();

        var sellerOption = sellerRepository.findById(sellerId);
        if(sellerOption.isEmpty())
            throw ApplicationException.builder()
                    .title("seller does not exists")
                    .Description("please login from proper seller option")
                    .build();
        Optional<Post> postOption = postRepository.findPostByName(post.getTitle());
        if(postOption.isPresent()){
            throw ApplicationException.builder()
                    .title("PostAlreadyExists")
                    .Description("Title  Already Exists")
                    .status(301)
                    .build();
        }

        String title = post.getTitle();
        String name = title.trim().replace(' ','-');
        post.setName(name);

        var locationOptional = locationRepository.findLocationByDistrict(post.getLocation().getDistrict());
        locationOptional.ifPresent(post::setLocation);

        /*try {
            var bytes1 =   ImageUtility.getBufferedImage(image1);
            var bytes2 =   ImageUtility.getBufferedImage(image2);

            post.setImageData1(new SerialBlob(bytes1));
            post.setImageData2(new SerialBlob(bytes2));

        } catch ( SQLException e) {
            throw ApplicationException.builder()
                    .title("error saving data due to database")
                    .Description("try to choose another format for images")
                    .build();
        }
        catch (IOException e){
            throw ApplicationException.builder()
                    .title("error saving data due to File Format")
                    .Description("try to choose another format for images")
                    .build();
        }*/


        //Saving Image to Aws S3 bucket
        /*String uuid = UUID.randomUUID() + ".jpg".toString();
        remoteStorage.uploadFile(image1, uuid1);
        post.setImage1(uuid1);

        String uuid2 = UUID.randomUUID() + ".jpg".toString();
        remoteStorage.uploadFile(image2, uuid2);
        post.setImage2(uuid2);*/

        for (int i = 0; i < files.size(); i++) {
            String uuid = UUID.randomUUID() + ".jpg".toString();
            MultipartFile image = files.get(i);
            remoteStorage.uploadFile(image, uuid);

            switch (i) {
                case 0:
                    post.setImage1(uuid);
                    break;
                case 1:
                    post.setImage2(uuid);
                    break;
                case 2:
                    post.setImage3(uuid);
                    break;
                case 3:
                    post.setImage4(uuid);
                    break;
                case 4:
                    post.setImage5(uuid);
                    break;
                case 5:
                    post.setImage6(uuid);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported number of images");
            }
        }


        var categoryOptional =  categoryRepository.findCategoryByName(post.getCategory().getName());
        categoryOptional.ifPresent(post::setCategory);


        post.setViews(0);
        postRepository.save(post);

        if(categoryOptional.isPresent()){
            var category = categoryOptional.get();
            List<Post> posts = category.getPosts();
            posts.add(post);
            category.setPosts(posts);
            categoryRepository.save(category);
        }

        if(locationOptional.isPresent()){
            var location = locationOptional.get();
            var locationPosts = location.getPosts();
            locationPosts.add(post);
            location.setPosts(locationPosts);
            locationRepository.save(location);
        }
        var seller = sellerOption.get();
        var sellerPosts = seller.getPosts();
        sellerPosts.add(post);
        seller.setPosts(sellerPosts);
        sellerRepository.save(seller);


    }




    @Override
    public void register(Seller seller) throws AccountCreationException {
        boolean eExist = sellerRepository.findSellerByUsername(seller.getUsername()).isPresent();
        if(eExist){
            throw AccountCreationException.builder()

                    .title("RegistrationAlreadyExists")
                    .Description("Username Already Exists")
                    .status(303)
                    .build();
        }
        sellerRepository.save(seller);
    }

    @Override
    public String update(SellerUpdateProfileRequest request) {
        Seller seller = sellerRepository.findSellerByUsername(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Seller Not Found"));
        seller.setEmail(request.getEmail());
        seller.setPhoneNumber(request.getPhoneNumber());

        return "";
    }

    @Override
    public List<SellerAllPostResponse> getSellerAllPosts(int sellerId) {
        List<Post> posts = postRepository.findPostBySellerId(sellerId);

        if (posts.isEmpty()) {
            throw new BadRequestException("Seller Not Found");
        }

        return posts.stream()
                .map(post -> {
                    SellerAllPostResponse response = new SellerAllPostResponse();
                    response.setPostId(post.getId());
                    response.setPostName(post.getTitle());
                    response.setDescription(post.getDescription());
                    response.setViews(post.getViews());
                    response.setStatus(post.getIsBlocked() ? "Blocked" : "non-blocked");
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String deleteSellerPostById(int postId) {
        postRepository.findById(postId).ifPresentOrElse(post->{
            postRepository.delete(post);
        }, ()-> {
            throw new BadRequestException("Post Not Found");
        });
        return "Post Deleted Successfully";
    }

    @Override
    public String changePassword(int sellerId, ChangePasswordRequest request) {
        sellerRepository.findById(sellerId)
                .ifPresentOrElse(seller -> {
            if (seller.getPassword().trim().equals(request.getOldPassword().trim())) {
                if(request.getNewPassword().trim().equals(request.getConfirmPassword().trim())){
                    seller.setPassword(request.getNewPassword());
                } else {
                    throw new BadRequestException("Confirm Passwords do not match");
                }
            } else {
                throw new BadRequestException("Old Password Not Matched");
            }
        },  ()-> {
            throw new BadRequestException("Seller Not Found");
        });
        return "";
    }
}
