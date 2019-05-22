package com.qhit.springbootmybatisdemo1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        //首先进行路径匹配,"/"为访问首页，使所有人都可以访问首页
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/addUser/**").hasRole("VIP1")
                .and()
                .logout().permitAll()//让注销按钮可以被所有用户访问
                ;
                //.antMatchers("/findAll/**").hasRole("VIP2")
        //接着让/findAll请求下的所有请求,都必须具备”VIP1“角色才能访问
        //.antMatchers("/请求/**").hasRole("角色")
        //以此格式类推，进行角色控制

        //开启自动配置的登陆功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/tologin");
        //1.让/login请求来到登陆页，
        //2.如果登陆错误，会重定向到login/error请求，表示登陆失败
        //3.如果没有权限就会来到自动生成的登陆页面

        //开启自动配置的注销用户功能
        http.logout().logoutSuccessUrl("/");//注销成功后，来到首页
        http.logout().logoutSuccessUrl("/");
        //1.访问/logout 表示用户注销，清空session
        //2.注销成功会返回 login?logout 请求页面

        http.rememberMe().rememberMeParameter("remeber");//记住我
        http.csrf().disable();//关闭默认csrf认证
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义一个用户角色，注意：非数据库！     必须对密码加密后才能进行验证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder()
                .encode("123")).roles("VIP1")
                .and()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("lisi").password(new BCryptPasswordEncoder()
                .encode("123")).roles("VIP1","VIP2");

        //使用.and()可以再定义一个用户

    }


    @Override
    public void configure(WebSecurity web) throws  Exception{
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}