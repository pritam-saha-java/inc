package com.incallup.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "tbl_category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @Column(name = "idCategory")
    public Integer idCategory;

    @Column(name = "category_name")
    public String category_name;
}
