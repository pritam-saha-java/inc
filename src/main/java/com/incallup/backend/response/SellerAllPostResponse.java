package com.incallup.backend.response;

import lombok.Data;

@Data
public class SellerAllPostResponse {

    private Integer postId;
    private String postName;
    private String description;
    private Integer views;
    private Integer callsClicked;
    private Integer whatsappClicked;
    private Integer telegramClicked;
    private String status;

}
