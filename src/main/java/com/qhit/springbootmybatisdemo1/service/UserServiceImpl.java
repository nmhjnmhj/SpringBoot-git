package com.qhit.springbootmybatisdemo1.service;

import com.qhit.springbootmybatisdemo1.beans.Page;
import com.qhit.springbootmybatisdemo1.beans.User;
import com.qhit.springbootmybatisdemo1.dao.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Usermapper um;
    @Override
    public User finduserbyid(Integer id) {
        return um.findUserbyid(id);
    }

    @Override
    public User finUserbyup(String user, String pwd) {
        return um.finUserbyup(user,pwd);
    }

    @Override
    public boolean addUser(String user, String pwd) {
        Integer i=um.addUser(user,pwd);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return um.findAll();
    }

    @Override
    public Page getNewList(int index1, int currPageNo, int pageSize) {
        //设置当前页和页面中的的数据大小
        Page page = new Page();
        page.setCurrPageNo(currPageNo);//设置当前页
        page.setPageSize(pageSize);//页面中能放多少条数据
        //4.在业务层查询总记录数
        //查询数据层获取总记录数
        int count  =um.getNewsCount();
        System.out.println("总记录数"+count);
        //根据总记录数计算总页数
        page.setTotalCount(count);
        //返回dao层在写方法
        //5.分页 ===5.1转到dao层写分页实现
        List<User> list=um.getNewsA(index1,currPageNo, pageSize);
        page.setNewsList(list);
        return page;
    }


    @Override
    public boolean deladmin(int uid) {
        return um.deladmin(uid);
    }
}
