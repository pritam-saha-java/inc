package com.incallup.backend.response;

import com.incallup.backend.domain.Category;
import lombok.Data;

import java.time.Instant;

@Data
public class SellerAllOrdersResponse {

    private Instant createdAt;
    private String title;
    private Category category;
    private Integer id;
}
