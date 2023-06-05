package com.nhnacademy.minidooray_gateway.handler;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.service.AccountService;
import com.nhnacademy.minidooray_gateway.service.UserInfoBeanForRedis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserInfoBeanForRedis userInfoBeanForRedis;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("username", authentication.getName());
//        session.setAttribute("userUUID", authentication);
        response.addCookie(new Cookie("SESSION", session.getId()));
        response.sendRedirect("/");

    }
}
