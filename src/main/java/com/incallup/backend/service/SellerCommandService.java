package com.incallup.backend.service;

import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.AccountCreationException;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.request.ChangePasswordRequest;
import com.incallup.backend.response.SellerAllPostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import com.incallup.backend.response.SellerUpdateProfileRequest;

/**
 *
 * -- Verify Seller (POST) -- delayed
 * -- Create Post (POST) -- done
 * -- Register (POST) -- done
 */

public interface SellerCommandService {

    void createPost(Post post, Integer sellerId, MultipartFile multipartFile1, MultipartFile multipartFile2) throws ApplicationException, IOException;

    void register(Seller seller) throws AccountCreationException;

    String update(SellerUpdateProfileRequest request);

    List<SellerAllPostResponse> getSellerAllPosts(int sellerId);

    String deleteSellerPostById(int postId);

    String changePassword(int sellerId, ChangePasswordRequest request);
}
