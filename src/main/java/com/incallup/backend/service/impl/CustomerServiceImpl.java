package com.incallup.backend.service.impl;


import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements{

    public Post searchByTitle(String title) throws ApplicationException{
        String nS = CustomerRepository.searchByTitle(customer.findByTitle()).isPresent();
        if(nS){
            throw ApplicationException.builder()
                    .title("TitleNotFound")
                    .Description("Title Not Found")
                    .status(503)
                    .build();
        }
    }

    public List<Post> searchByCategory(String category) throws ApplicationException{
        String nSExists = CustomerRepository.searchByCategory(customer.findByCategory).isPresent();
        if(nSExists){
            throw ApplicationException.builder()
                    .title("CategoryNotFound")
                    .Description("Category Not Found")
                    .status(503)
                    .build();
        }
    }

    public List<Post> searchByCategoryAndLocation(String category, String location) throws ApplicationException{
        String n = CustomerRepository.searchByCategoryAndLocation(String category, String location).isPresent();
        if(n){
            throw ApplicationException.builder()
                    .title("NotFoundByCategoryAndLocation")
                    .Description("Not Found By Category And Location")
                    .status(503)
                    .build();
        }
    }


}
