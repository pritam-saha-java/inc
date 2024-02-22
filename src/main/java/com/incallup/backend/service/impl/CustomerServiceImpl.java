package com.incallup.backend.service.impl;


import com.incallup.backend.domain.Post;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements{

    public Post searchByTitle(String title) throws ApplicationException{
        String nS = CustomerRepository.searchByTitle(customer.getId()).isPresent();
    }
}
