//package org.example.auth;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//
//import java.io.IOException;
//
//@WebFilter("/afterLoginFilter")
//public class AfterLoginFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        // 권한 확인
//        Authentication authentication = (Authentication) httpRequest.getUserPrincipal();
//        if (authentication != null) {
//            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
//                // ADMIN 권한 리다이렉트
//                httpResponse.sendRedirect("/admin");
//                return;
//            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
//                // USER 권한 리다이렉트
//                httpResponse.sendRedirect("/user");
//                return;
//            }
//        }
//
//        // 교수는 기본 리다이렉트
//        chain.doFilter(request, response);
//    }
//
//}