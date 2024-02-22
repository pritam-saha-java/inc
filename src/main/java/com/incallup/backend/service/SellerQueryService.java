package com.incallup.backend.service;


import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.IdNotFoundException;

import java.util.List;

/**
 * Will Contain all GET Operations for Sellers
 * -- View Seller Profile by Seller id
 * -- View Post By PostId
 * -- Get List Of Posts by Seller id
 * -- Get Metrics For Post by Post id -- delayed
 * */
public interface SellerQueryService {

    Seller getSellerById(Integer sellerId) throws IdNotFoundException;
    Post getPostById(Integer postId) throws IdNotFoundException;

    List<Post> getPostsBySellerId(Integer sellerId) throws IdNotFoundException;



}
