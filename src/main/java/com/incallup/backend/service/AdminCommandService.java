package com.incallup.backend.service;


import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.exception.IdNotFoundException;
import org.springframework.web.multipart.MultipartFile;

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

     void blockPost (Integer postId) throws IdNotFoundException;

     void blockSeller(Integer sellerId) throws IdNotFoundException;

     void createLocation(Location location) throws ApplicationException;

     void createCategory(Category category, MultipartFile multipartFile) throws ApplicationException;

     void updateCategory(Category category) throws ApplicationException;

     void updateLocation(Location location) throws ApplicationException;


}
