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

   @Insert("insert into user (name,account_id,token,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) values(#{name},#{account_id},#{token},#{GMT_CREATE},#{GMT_MODIFIED},#{avatar_url})")
    public void insert(User user);
     @Select("SELECT * FROM user WHERE name = #{login}")
     User findByLogin(@Param("login") String login);

     @Select("select * from user")
      public List<User>  selectAll();

     @Select("select * from user where token = #{token}")
      User findByToken(@Param("token") String token);

     @Select("select * from user where id = #{id}")
      User findUserById(int id);
     @Select("select top 1 id from user where name = ${name}")
      int findIdByUserName(String name);
}
