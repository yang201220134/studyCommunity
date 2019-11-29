package com.ysy.studycommunity.dto;

public class GitHubUser {
    private String login;
    private String id;


    public GitHubUser() {
    }

    public GitHubUser(String login, String id) {
        this.login = login;
        this.id = id;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
