package com.qhit.springbootmybatisdemo1.service;

import com.qhit.springbootmybatisdemo1.beans.QuanXian;
import com.qhit.springbootmybatisdemo1.dao.QuanXianMapper;
import com.qhit.springbootmybatisdemo1.dao.Usermapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private Usermapper um;
    @Resource
    private QuanXianMapper qm;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.qhit.springbootmybatisdemo1.beans.User user = um.findByUserName(username);
        if (user != null) {
            System.out.println("user信息:" + user.getUsername());
            List<QuanXian> qx = qm.findByAdminUserId(user.getId());
            for (QuanXian q : qx) {
                System.out.println("权限:" + q.getName());
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (QuanXian permission : qx) {
                if (permission != null && permission.getName() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String password = passwordEncoder.encode(user.getPassword());
//            System.out.println("加密密码："+password);
            System.out.println("user"+user);
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            logger.error(username + "不存在");
            return null;
        }
    }
}
