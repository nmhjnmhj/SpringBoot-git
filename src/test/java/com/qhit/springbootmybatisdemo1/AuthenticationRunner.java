package com.qhit.springbootmybatisdemo1;


import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationRunner {

    @Test
    public void test1(){
        //通过UserDetailsService获取到持久层信息
        UserDetailsService userDetailsService =new InMemoryUserDetailsManager();
        //具体实现userDetailsService,并创建一个用户
        ((InMemoryUserDetailsManager)userDetailsService).createUser(
                User.withUsername("用户名！").password("pwd").roles("权限！").build());
        UserDetails userDetails=userDetailsService.loadUserByUsername("用户名！");
        System.out.println(userDetails);

        //获取到持久层userDetails后，指定处理的provider
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        //将userDetails配置到provider中去，从userDetailsService获取数据来源
        provider.setUserDetailsService(userDetailsService);
        List<AuthenticationProvider> providers=new ArrayList<>();
        providers.add(provider);
        AuthenticationManager manager =new ProviderManager(providers);
        //获取provider集合后，通过manager进行身份验证

        //manager完成了组装后，调用authenticate方法，其参数是Authentication的对象实例
        //获取外部传入的用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken("用户名！","pwd");
        Authentication result=null;
        //尝试通过manager去验证用户名密码是否正确
        result=manager.authenticate(authenticationToken);
        //result会返回权限信息
        System.out.println(authenticationToken);
        System.out.println(result);
    }
}
