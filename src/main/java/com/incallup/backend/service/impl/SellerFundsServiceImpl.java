package com.incallup.backend.service.impl;

import com.incallup.backend.domain.TopUpPackageEntity;
import com.incallup.backend.repository.TopUpPackageRepository;
import com.incallup.backend.service.SellerFundsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SellerFundsServiceImpl implements SellerFundsService {

    private final TopUpPackageRepository pkgRepository;

    public SellerFundsServiceImpl(TopUpPackageRepository pkgRepository) {
        this.pkgRepository = pkgRepository;
    }

    @Override
    public List<TopUpPackageEntity> getAllTopUpPackages() {
        return StreamSupport.stream(pkgRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
