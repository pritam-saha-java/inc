package com.incallup.backend.service.impl;


import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import com.incallup.backend.exception.LogoutException;
import com.incallup.backend.repository.PostRepository;
import com.incallup.backend.repository.SellerRepository;
import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * This Service should implement both {@link com.incallup.backend.service.SellerCommandService} and {@link com.incallup.backend.service.SellerQueryService} interfaces
 **/

@Service
@RequiredArgsConstructor
public class SellerService implements SellerQueryService, SellerCommandService {

    @Autowired
    private final SellerRepository sellerRepository;
    @Autowired
    private final PostRepository postRepository;


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
    public void createPost(@Valid Post post, Integer sellerId) throws ApplicationException {
        Optional<Post> eExist = postRepository.findPostByTitle(post.getTitle());
        if(eExist.isPresent()){
            throw ApplicationException.builder()
                    .title("PostAlreadyExists")
                    .Description("Title  Already Exists")
                    .status(301)
                    .build();
        }
        postRepository.save(post);
    }

    @Override
    public void register(Seller seller) throws ApplicationException {
        boolean eExist = sellerRepository.findSellerByUsername(seller.getUsername()).isPresent();
        if(eExist){
            throw ApplicationException.builder()
                    .title("RegistrationAlreadyExists")
                    .Description("Username Already Exists")
                    .status(500)
                    .build();
        }
        sellerRepository.save(seller);
    }




}
