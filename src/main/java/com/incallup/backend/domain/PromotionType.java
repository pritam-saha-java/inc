package com.incallup.backend.domain;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public enum PromotionType {

    DIAMOND("diamond"),CROWN("crown");

     final String name;


}
