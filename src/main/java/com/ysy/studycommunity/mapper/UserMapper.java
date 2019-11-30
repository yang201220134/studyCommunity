package com.ysy.studycommunity.mapper;

import com.ysy.studycommunity.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
   // @Select("SELECT * FROM CITY WHERE state = #{state}")
  //  City findByState(@Param("state") String state);
   @Insert("insert into user (name,account_id,token,GMT_CREATE,GMT_MODIFIED) values(#{name},#{account_id},#{token},#{GMT_CREATE},#{GMT_MODIFIED})")
    public void insert(User user);
     @Select("SELECT * FROM user WHERE name = #{login}")
     User findByLogin(@Param("login") String login);

     @Select("select * from user")
      public List<User>  selectAll();
}
