package com.ysy.studycommunity.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id;
    private String redirect_uri;
    private String scope;
    private String state;
    private String client_secret;
    private String code;



}
