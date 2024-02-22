package com.incallup.backend.service;

import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.ApplicationException;

/**
 *
 * -- Verify Seller (POST) -- delayed
 * -- Create Post (POST)
 * -- Register (POST)
 */

public interface SellerCommandService {

    void createPost(Post post,Integer sellerId) throws ApplicationException;

    void register(Seller seller) throws ApplicationException;

}
