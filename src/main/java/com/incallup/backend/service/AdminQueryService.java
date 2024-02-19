package com.incallup.backend.service;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.domain.Post;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.IdNotFoundException;

import java.util.List;

/**
 * will contain only GET Operations for admin
 *  -- Return List of all Sellers -- done
 *  -- Return List of all Posts -- done
 *  -- Return List of all Categories -- done
 *  -- Return List of all Locations --done
 *  -- Return Location By ID -- done
 *  -- Return Category By ID -- done
*/
public interface AdminQueryService {

    public List<Seller> listSellers();

    public List<Post> listPosts();

    public List<Category> listCategories();

    public List<Location> listLocations();

    public Location getLocationById(Integer locationId) throws IdNotFoundException;

    public Category getCategoryById(Integer categoryId) throws IdNotFoundException;


}
