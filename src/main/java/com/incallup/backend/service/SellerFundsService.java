package com.incallup.backend.service;

import com.incallup.backend.domain.TopUpPackageEntity;

import java.util.List;

public interface SellerFundsService {
    List<TopUpPackageEntity> getAllTopUpPackages();
}
