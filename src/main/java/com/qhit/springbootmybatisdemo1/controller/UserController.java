package com.qhit.springbootmybatisdemo1.controller;

import com.qhit.springbootmybatisdemo1.beans.Page;
import com.qhit.springbootmybatisdemo1.beans.User;
import com.qhit.springbootmybatisdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping("/find")
    @ResponseBody
    public User find(){
        return us.finduserbyid(4);
    }

    @PostMapping(value="/login")
    public String login(@RequestParam("user")String user,
                        @RequestParam("pwd")String pwd,
                        Map<String,Object> map, HttpSession session){

        User u=us.finUserbyup(user, pwd);
        if(u!=null){
            System.out.println("登陆成功！");
            session.setAttribute("user",u);
            return "redirect:/findAll";
        }else{
            map.put("msg","登陆失败");
            return "login";
        }
        //return "首页";
    }

    @ResponseBody
    @PostMapping(value = "/addUser")
    public String addUser(@RequestParam("user")String user,
                          @RequestParam("pwd")String pwd){
        if(us.addUser(user,pwd)){
            return "添加成功！";
        }else{
            return "添加失败！";
        }
    }


    @RequestMapping(value = "/findAll")
    public String selAll(Map<String,Object> map){
        map.put("users",us.findAll());
        /*if(map.get("users")!=null){
            map.put("mag","不是空！");
        }else{
            map.put("mag","空！");
        }*/
        return "main";
    }


    @RequestMapping(value="/fenye")
    public  String fenye( HttpServletRequest request,
                          HttpSession session,HttpServletResponse response) throws IOException {
        //1从前台获取数据 第一次执行时一定得用非空校验
        String str =(String)request.getParameter("index");
        System.out.println(str);
        if(str==null){
            str="1";
        }
        //System.out.println("fieosfjoiefnioesfnosifoei"+str);
        Integer index=Integer.parseInt(str);
        String str1 =(String)request.getParameter("pageSize");
        if(str1==null){
            str1="5";
        }
        System.out.println("页大小"+str1);
        Integer pageSize=Integer.parseInt(str1);
        //3.将前台传过来的数据放到业务逻辑层,并将数据放置在page中 \
        int index1=(index-1)*pageSize;
        Page p = us.getNewList(index1,index, pageSize);
        System.out.println(p);
        session.setAttribute("page", p);
        return "main";

    }

    @ResponseBody
    @RequestMapping(value="/deladmin")
    public  boolean deladmin( HttpServletRequest request,HttpServletResponse resp,
                           HttpSession session) throws IOException{
        String items = request.getParameter("delitems");// System.out.println(items);
        String[] strs = items.split(",");
        boolean flag=false;
        for (int i = 0; i < strs.length; i++) {
            try {
                int a = Integer.parseInt(strs[i]);
                flag=us.deladmin(a);
            } catch (Exception e) {
            }
        }
        return flag;
//        resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out=resp.getWriter();
//        out.print(flog);
//        out.flush();
//        out.close();




    }
}
