package com.nhnacademy.minidooray_gateway.handler;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.service.AccountService;
import com.nhnacademy.minidooray_gateway.service.UserInfoBeanForRedis;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Setter
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 로그인 성공시 처리 커스텀 핸들러
     * #TODO Redis로 변경 요망(Session Redis에 태우기)
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */

    private final RedisTemplate<String, String> redisTemplate;
    private final UserInfoBeanForRedis userInfoBeanForRedis;

    public LoginSuccessHandler(RedisTemplate<String, String> redisTemplate, UserInfoBeanForRedis userInfoBeanForRedis) {
        this.redisTemplate = redisTemplate;
        this.userInfoBeanForRedis = userInfoBeanForRedis;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //super.onAuthenticationSuccess(request, response, authentication);

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

        session.setAttribute("username", userDetails.getUsername());
        session.setAttribute("authority", authorities.get(0).getAuthority());

//        response.sendRedirect("/dooray/board");
    }
}
