package com.incallup.backend.service;

import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.AccountCreationException;
import com.incallup.backend.exception.ApplicationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * -- Verify Seller (POST) -- delayed
 * -- Create Post (POST) -- done
 * -- Register (POST) -- done
 */

public interface SellerCommandService {

    void createPost(Post post, Integer sellerId, MultipartFile multipartFile1, MultipartFile multipartFile2) throws ApplicationException, IOException;

    void register(Seller seller) throws AccountCreationException;

}
