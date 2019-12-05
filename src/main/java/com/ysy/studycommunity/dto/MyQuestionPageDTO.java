package com.ysy.studycommunity.dto;

import com.ysy.studycommunity.mapper.QuestionMapper;
import com.ysy.studycommunity.model.Question;
import com.ysy.studycommunity.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
@Data
public class MyQuestionPageDTO{

    private List<Question> questionList;
    private boolean hasshowPrevious;
    private boolean hasGotoFirstPage;
    private boolean hasshownNext;
    private boolean hasGotoEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer questionCount;
    private  Integer everyShowPageCount;
    private Integer totalPage;
    private Integer offset;
    private Integer pagesSize;
    private Boolean isAddMid  = false;
    private User user = new User();

    @Autowired
    private QuestionMapper questionMapper;

    public void initPageData(Integer currenpagetwo,Integer everyShowPageCount,User user){
        this.user = user;
        this.questionCount = questionMapper.getProfileQuestionSum(user.getId());
        this.currentPage = currenpagetwo;
        this.everyShowPageCount = everyShowPageCount;
        //初始化总页数
        if(questionCount % everyShowPageCount ==0){
            totalPage = questionCount / everyShowPageCount;
        }else {
            totalPage = questionCount / everyShowPageCount + 1;
        }

        if(currentPage==1){
            hasshowPrevious = false;
        }else {
            hasshowPrevious = true;
        }

        if(currentPage==totalPage){
            hasshownNext = false;
        }else {
            hasshownNext = true;
        }


        offset = everyShowPageCount*(currentPage - 1);
        isAddMid = false;
        if(currentPage > totalPage){
            currentPage = totalPage;
        }
        if(currenpagetwo < 1)
        {
            currentPage = 1;
        }
        for(int i =1;i<=3;i++){
            if(currentPage - i> 0){
                pages.add(0,currentPage - i);
            }
            if(!isAddMid){
                pages.add(currentPage);
                isAddMid = true;
            }
            if(currentPage + i <= totalPage){
                pages.add(currentPage+i);
            }
        }

        pagesSize = pages.size();


        if(pages.contains(1)){
            hasGotoFirstPage =false;
        }else {
            hasGotoFirstPage = true;
        }

        if(pages.contains(totalPage)){
            hasGotoEndPage = false;
        }else {
            hasGotoEndPage = true;
        }

    }

}
