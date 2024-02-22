package com.incallup.backend.service;

import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;

import java.util.List;

/**
 * Customer only has get operations in this phase
 * -- Search by title
 * -- Search by category
 * -- Search by category & location
 * -- Search by location
 */
public interface CustomerService {

    Post searchByTitle(String title) throws ApplicationException;
    List<Post> searchByCategory(String category) throws ApplicationException;
    List<Post> searchByCategoryAndLocation(String category,String location) throws ApplicationException;
    List<Post> searchByLocation(String location) throws ApplicationException;


}
