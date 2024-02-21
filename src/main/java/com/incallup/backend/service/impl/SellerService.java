package com.incallup.backend.service.impl;

import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * This Service should implement both {@link com.incallup.backend.service.SellerCommandService} and {@link com.incallup.backend.service.SellerQueryService} interfaces
 **/

@Service
@RequiredArgsConstructor
public class SellerService implements SellerQueryService, SellerCommandService {
}
