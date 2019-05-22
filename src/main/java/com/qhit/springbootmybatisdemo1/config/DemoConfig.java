package com.qhit.springbootmybatisdemo1.config;

import com.qhit.springbootmybatisdemo1.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启spring security注解功能
public class DemoConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DemoService demoService;
    @Autowired
    //自定义登陆失败跳转的页面
    private SecurityAuthenticationFailHandler fail;
    @Autowired
    //自定义登陆成功跳转的页面
    private SecurityAuthenticationSuccessHandler success;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.csrf().disable() //禁用csrf
                .formLogin().loginPage("/tologin") //开启自定义登陆页面action方法  .loginProcessingUrl("/loginSecurity")
                .successHandler(success)//登陆成功
                .failureHandler(fail)//登陆失败
                .permitAll()//以上允许访问
                .and()
                .headers().frameOptions().disable()//允许表头出现（控制核心，显示数据）
                .and()
                .authorizeRequests()//指定用户可以访问以下请求
                .antMatchers("/tologin", "/logout").permitAll()//登陆页面和退出页面
                .antMatchers("/js/**", "/css/**").permitAll()//静态资源处理
                .anyRequest()//下面所有请求
                .authenticated();//都需要进行认证


    }



    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //授权
        auth.userDetailsService(demoService).passwordEncoder(passwordEncoder());
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Override
    public void configure(WebSecurity web) throws  Exception{
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }*/

}
