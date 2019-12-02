package com.ysy.studycommunity.dto;

import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class QuestionDTO {

    private Question question;
    private User user;
}

