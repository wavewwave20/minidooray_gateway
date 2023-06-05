package com.nhnacademy.minidooray_gateway.handler;

import com.nhnacademy.minidooray_gateway.service.UserInfoBeanForRedis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserInfoBeanForRedis userInfoBeanForRedis;

    public LoginSuccessHandler(RedisTemplate<String, String> redisTemplate, UserInfoBeanForRedis userInfoBeanForRedis) {
        this.redisTemplate = redisTemplate;
        this.userInfoBeanForRedis = userInfoBeanForRedis;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        HttpSession session = request.getSession(false);
        session.setMaxInactiveInterval(259200); //TODO: 왜 필요한지


        redisTemplate.opsForHash().put(session.getId(), "username", userDetails.getUsername());
        redisTemplate.opsForHash().put(session.getId(), "authority", authorities.get(0).getAuthority());
        redisTemplate.opsForHash().put(session.getId(), "userEmail", userInfoBeanForRedis.getUserEmail());
        redisTemplate.opsForHash().put(session.getId(), "userUUID", userInfoBeanForRedis.getUserUUId());
        redisTemplate.opsForHash().put(session.getId(), "userNickName", userInfoBeanForRedis.getUserNickname());
        redisTemplate.boundHashOps(session.getId()).expire(258900, TimeUnit.SECONDS);

        log.info("Session ID: " + session.getId());

        response.sendRedirect("/dooray/board");
    }
}
