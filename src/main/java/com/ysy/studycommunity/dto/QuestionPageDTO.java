package com.ysy.studycommunity.dto;

import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import lombok.Data;

import java.util.Date;


@Data
public class QuestionPageDTO {

  private Question question;
  private User fawentiUser;
  private User loginUser;




}
