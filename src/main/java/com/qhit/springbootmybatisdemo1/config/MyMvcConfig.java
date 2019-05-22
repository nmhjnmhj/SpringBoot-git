package com.qhit.springbootmybatisdemo1.config;

import com.qhit.springbootmybatisdemo1.ljq.LoginLjq;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    //此方法为视图解析器，设置视图映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        //直接返回WebMvcConfigurerAdapter
        //因为该类为抽象类，故实例化的时候直接实现抽象方法
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter(){
            //在具体实现中直接添加一个视图解析器，可返回
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("main");
                registry.addViewController("/tologin").setViewName("login");
                registry.addViewController("/toaddUser").setViewName("addUser");
            }
            //addInterceptors就是注册一个拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //addInterceptor添加一个拦截器，直接new拦截器类
                //addPathPatterns指明哪个请求需要进行拦截，/**为全部拦截
                //excludePathPatterns排除哪个请求不需要进行拦截，比如访问登陆页面的请求
                registry.addInterceptor(new LoginLjq()).addPathPatterns("/main.html")
                .excludePathPatterns("/","/find","/login","/main");
                //使用逗号分开请求
            }
        };
        return adapter;
    }
}
