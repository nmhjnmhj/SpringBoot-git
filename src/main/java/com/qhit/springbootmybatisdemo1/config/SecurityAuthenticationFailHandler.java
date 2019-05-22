package com.qhit.springbootmybatisdemo1.config;

import com.sun.deploy.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Liu
 * @create 2019/4/13 10:04
 */
@Component("securityAuthenticationFailHandler")
public class SecurityAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("error")
    private String failureUrl;// 权限认证失败地址

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException{
        logger.debug(exception.getMessage() + " " + failureUrl);
        /** 跳转到指定页面 */
        String redirectUrl = "/?message=" + URLEncoder.encode(exception.getMessage(),"UTF-8");
        new DefaultRedirectStrategy().sendRedirect(request, response,redirectUrl);
    }
}
