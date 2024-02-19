package com.incallup.backend.service;


import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;

/**
 *  will contain PUT & POST operations for admin
 *  -- Block Post By ID(PUT) -- done
 *  -- Block Seller By ID (PUT) -- done
 *  -- Create Location (POST) -- done
 *  -- Create Category (POST) -- done
 *  -- Update Location (PUT) -- done
 *  -- Update Category (PUT) -- done
 */
public interface AdminCommandService {

    public void blockPost (Integer postId) throws IdNotFoundException;

    public void blockSeller(Integer sellerId) throws IdNotFoundException;

    public void createLocation(Location location) throws ApplicationException;

    public void createCategory(Category category) throws ApplicationException;


    public void updateCategory(Category category) throws ApplicationException;

    public void updateLocation(Location location) throws ApplicationException;


}
