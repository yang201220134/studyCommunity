package com.ysy.studycommunity.service;

import com.ysy.studycommunity.dto.PageDTO;
import com.ysy.studycommunity.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class PageService {

    @Autowired
    private QuestionService questionService;


    public PageDTO getPageDTO(Integer currentPage, Integer everyPageShowCount, HttpServletRequest request){
        // List<Question> questionList = questionMapper.getQuestionList();
        List<QuestionDTO> questionDTOListData = questionService.getQuestionDTOList(request,currentPage,everyPageShowCount);


        // for (QuestionDTO questionDTOListDatum : questionDTOListData) {
        //     questionDTOListDatum.getQuestion().setDescription("resert");
        // }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOListData);
        pageDTO.setQuestionCount(questionService.getQuestionCount());

        pageDTO.initPageData(currentPage,everyPageShowCount);

        System.out.println("currentPage--------------"+currentPage);

        return pageDTO;
    }
}
