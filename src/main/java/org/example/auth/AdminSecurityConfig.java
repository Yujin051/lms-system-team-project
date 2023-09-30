//package org.example.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.example.constant.RoleType;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//@Order(3)
//public class AdminSecurityConfig {
//
//
//
//    @Bean
//    protected SecurityFilterChain AdminFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests(requests -> requests
//                        .requestMatchers(
//                                "/h2-console/**"    // H2 콘솔 허용
//                        ).permitAll()
//                        .requestMatchers("/notice/new/**","notice/modify/**", "notice/update/**", "notice/delete/**", "").hasRole(RoleType.ADMIN.toString())
//                        .requestMatchers("/my/**", "/manager/index").hasAnyRole(RoleType.ADMIN.toString(), RoleType.USER.toString())
//                )
//                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
//
//                .and()
//                // 로그인
//                .formLogin(login -> login
//                        .loginPage("/admin/login") // 로그인 페이지
//                        .defaultSuccessUrl("/") // 성공 시 이동하는 페이지
//                        .usernameParameter("adminId") // 이메일을 아이디로 사용
//                        .failureUrl("/admin/login/error")) // 실패페이지
//                // 로그아웃
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 페이지
//                        .logoutSuccessUrl("/")); // 로그아웃 성공 후 이동페이지
//
//
//        return http.build();
//
//    }
//
//
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    AuthenticationManager AdminAuthenticationManager(AuthenticationConfiguration auth) throws Exception {
//        return auth.getAuthenticationManager();
//    }
//
//    @Bean
//    public WebSecurityCustomizer AdminWebSecurityCustomizer() {
//        return(web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//}
