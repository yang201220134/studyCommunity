package com.ysy.studycommunity.model;

import lombok.Data;

import java.util.Date;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String creator;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;
    private String tag;
    private Date gmt_create;
    private Date gmt_modified;
    private int user_id;

   }
