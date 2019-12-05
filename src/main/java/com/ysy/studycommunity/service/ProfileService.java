package com.ysy.studycommunity.service;


import com.ysy.studycommunity.dto.MyQuestionPageDTO;
import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

   public MyQuestionPageDTO getQuestionPageDTOList(User user, Integer currentPage, Integer evryPageSize){

       MyQuestionPageDTO myQuestionPageDTO = new MyQuestionPageDTO();
       myQuestionPageDTO.initPageData(currentPage,evryPageSize,user);
       return myQuestionPageDTO;
    }
}
