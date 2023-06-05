package com.nhnacademy.minidooray_gateway.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginFilter
 * #TODO: Redis로 변경 요망(Session Redis에 태우기)
 * Security가 접근권한 설정할 수도 있지만 Redis사용시에 어떻게 될 지 몰라서 넣어뒀습니다
 */


@WebFilter
@Component
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if (requestURI.equals(httpRequest.getContextPath() + "/auth/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (requestURI.equals(httpRequest.getContextPath() + "/auth/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("username") != null);

        if (isLoggedIn) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/auth/login");
        }

    }
}
