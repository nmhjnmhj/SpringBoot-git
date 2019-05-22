package com.qhit.springbootmybatisdemo1.dao;

import com.qhit.springbootmybatisdemo1.beans.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface Usermapper {
    @Select("select * from user2 where id=#{id}")
    User findUserbyid(@Param("id")Integer id);

    @Select("select * from user2 where username=#{user} and password=#{pwd}")
    User finUserbyup(@Param("user")String user,@Param("pwd")String pwd);

    @Insert("insert into user2(username,password) values(#{user},#{pwd})")
    Integer addUser(@Param("user")String user,@Param("pwd")String pwd);

    @Select("select * from user2")
    List<User> findAll();

    @Select("SELECT COUNT(*) FROM user2")
    public int getNewsCount();
    @Select("select id,username,password from user2 limit #{index1},#{pageSize}")
    public List<User> getNewsA(@Param(value="index1")int index1,@Param(value="currPageNo")int currPageNo,@Param(value="pageSize")int pageSize);

    @Delete("delete from user2 where id=#{uid}")
    boolean deladmin(@Param("uid")int uid);
    //根据user找用户
    @Select("select * from user2 where username=#{username}")
    User findByUserName(@Param("username") String user);


}
