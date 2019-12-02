package com.ysy.studycommunity.model;


import lombok.Data;

@Data
public class QuestionCopy {
    private Integer id;
    private String title;
    private String description;
    private String creator;
    private Integer comment_count;
    private Integer like_count;
    private Integer view_count;
    private String tag;
    private long gmt_create;
    private long gmt_modified;
}
