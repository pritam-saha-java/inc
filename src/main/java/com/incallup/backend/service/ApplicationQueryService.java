package com.incallup.backend.service;

import com.incallup.backend.domain.Category;
import com.incallup.backend.domain.Location;
import com.incallup.backend.exception.ApplicationException;

import java.util.List;

public interface ApplicationQueryService {
     Category getCategoryByName(String categoryName) throws ApplicationException;
     Location getLocationByName(String locationName) throws ApplicationException;

     List<Location> getLocationByState(String state);

     String getSiteDescription();
}
