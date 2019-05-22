package com.qhit.springbootmybatisdemo1.ljq;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 登陆检查，拦截器
* */
public class LoginLjq implements HandlerInterceptor {

    //此为目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user=request.getSession().getAttribute("user");
        if(user==null){
            //未登陆，返回登陆页面
            //将该页面请求转发到登陆页面
            System.out.println("进入拦截器");
            request.setAttribute("msg","无权限，请先进行登陆操作！");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            //已登陆，放行
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
