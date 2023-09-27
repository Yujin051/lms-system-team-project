package org.example.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.constant.RoleType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Order(2)
public class SecurityConfig {



    @Bean
    protected SecurityFilterChain profFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(requests -> requests
                        .requestMatchers(
                                "/h2-console/**"   // H2 콘솔 허용
                        ).permitAll()
                        .requestMatchers("/admin","prof/**").hasRole(RoleType.ADMIN.toString())
                        .requestMatchers("/prof","prof/**").hasRole(RoleType.TEACHER.toString())
                        .requestMatchers("/student","student/**").hasRole(RoleType.USER.toString())
                        //.requestMatchers("/my/**").hasAnyRole(RoleType.ADMIN.toString(), RoleType.TEACHER.toString(), RoleType.USER.toString())
                )
                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))

                .and()
                // 로그인

                .formLogin(login -> login
                        .loginPage("/member/login") // 로그인 페이지
                        .loginProcessingUrl("/member/login")
                        .successHandler(new LoginSuccessHandler()) // 로그인 성공 시 이동하는 페이지 설정(권한별로)
                        .usernameParameter("userId") // 이메일을 아이디로 사용
                        .permitAll()    //로그인 페이지 접근 허용
                        .failureUrl("/member/login/error")) // 로그인 실패 페이지

                // 로그아웃
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 페이지
                        .logoutSuccessUrl("/member/login")) // 로그아웃 성공 후 이동페이지

                .exceptionHandling().accessDeniedPage("/denied");


        return http.build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return(web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers("/css/**","/js/**","/img/**");
    }
    
    //권한별 로그인 성공 시 이동하는 페이지
    public static class LoginSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication auth) throws IOException{
//            String url = switch (auth.getAuthorities().toString()) {
//                case "ADMIN" -> "/admin/";
//                case "USER" -> "/prof/";
//                case "TEACHER" -> "/user/";
//                default -> "/";
//            };
            
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                response.sendRedirect("/admin");
            }
            else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_TEACHER"))) {
                response.sendRedirect("/prof");
            }
            else if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                response.sendRedirect("/student");
            }
            else {
                response.sendRedirect("/");
            }

        }
    }

}
