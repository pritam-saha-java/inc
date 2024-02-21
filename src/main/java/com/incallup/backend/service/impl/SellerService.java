package com.incallup.backend.service.impl;

import com.incallup.backend.service.SellerCommandService;
import com.incallup.backend.service.SellerQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SellerService implements SellerQueryService, SellerCommandService {
}
