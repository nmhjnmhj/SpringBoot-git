package com.qhit.springbootmybatisdemo1.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Liu
 * @create 2019/4/13 10:06
 */
@Component("securityAuthenticationSuccessHandler")
public class SecurityAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 成功处理
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.debug("登录成功");
        /*
        super.onAuthenticationSuccess(request, response, authentication);// 直接调用父类方法
        */

        /*
        Map<String,String> map=new HashMap<>();
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));// 返回 JSON 信息
        */

        // TODO 用户登录日志、链接权限关联

        /** 跳转到指定页面 */
        new DefaultRedirectStrategy().sendRedirect(request, response, "/main.html");

    }
}