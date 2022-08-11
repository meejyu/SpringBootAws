package com.springboot.study.config.auth;

import com.springboot.study.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .headers().frameOptions().disable()// h2-console 화면을 사용하기 위해 해당옵션들을 disable 한다
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션
                    .antMatchers("/","/css/**","/images/**","/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole((Role.USER.name()))
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // Oauth2 로그인 기능에 대한 설정 진입점
                        .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들
                            .userService(customOauth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록, 리소스 서버(즉, 소셜 버시스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다
    }
}
