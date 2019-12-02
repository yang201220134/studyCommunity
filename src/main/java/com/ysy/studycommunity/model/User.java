package com.ysy.studycommunity.model;

import lombok.Data;

@Data
public  class User {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long GMT_CREATE;
    private Long GMT_MODIFIED;
    private String avatar_url;
}
