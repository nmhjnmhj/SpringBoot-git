package com.qhit.springbootmybatisdemo1.dao;

import com.qhit.springbootmybatisdemo1.beans.QuanXian;

import java.util.List;

public interface QuanXianMapper {
    List<QuanXian> findAll();
    List<QuanXian> findByAdminUserId(int userId);
}
