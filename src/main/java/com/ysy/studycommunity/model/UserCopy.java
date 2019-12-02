package com.ysy.studycommunity.model;

public class UserCopy {
    private Integer id;
    private String name;
    private String account_id;
    private String token;
    private Long GMT_CREATE;
    private Long GMT_MODIFIED;
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public UserCopy(Integer id, String name, String account_id, String token, Long GMT_CREATE, Long GMT_MODIFIED) {
        this.id = id;
        this.name = name;
        this.account_id = account_id;
        this.token = token;
        this.GMT_CREATE = GMT_CREATE;
        this.GMT_MODIFIED = GMT_MODIFIED;
    }

    public UserCopy() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGMT_CREATE() {
        return GMT_CREATE;
    }

    public void setGMT_CREATE(Long GMT_CREATE) {
        this.GMT_CREATE = GMT_CREATE;
    }

    public Long getGMT_MODIFIED() {
        return GMT_MODIFIED;
    }

    public void setGMT_MODIFIED(Long GMT_MODIFIED) {
        this.GMT_MODIFIED = GMT_MODIFIED;
    }
}
