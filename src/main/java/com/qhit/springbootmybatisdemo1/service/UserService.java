package com.qhit.springbootmybatisdemo1.service;

import com.qhit.springbootmybatisdemo1.beans.Page;
import com.qhit.springbootmybatisdemo1.beans.User;

import java.util.List;

public interface UserService {



    User finduserbyid(Integer id);
    User finUserbyup(String user,String pwd);
    boolean addUser(String user,String pwd);
    List<User> findAll();
    Page getNewList(int index1, int currPageNo, int pageSize);
    boolean deladmin(int uid);
}
