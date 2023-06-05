package com.nhnacademy.minidooray_gateway.config;


import com.nhnacademy.minidooray_gateway.handler.LoginSuccessHandler;
import com.nhnacademy.minidooray_gateway.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    //#TODO:REDIS에 세션 태우고 삭제하기 수정요망

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/login")
                    .successHandler(new LoginSuccessHandler(null))
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                .and()
                .oauth2Login()
                    .loginPage("/oauth2/authorization/github")
//                    .successHandler()
                    .defaultSuccessUrl("/")
//                .and()
//                    .sessionManagement()
//                    .sessionFixation()
//                    .none()
                .and()
                    .headers()
                    .frameOptions()
                    .disable()
                .and()
                    .sessionManagement()
                    .maximumSessions(1) // 동시 세션 수 설정 (원하는 값으로 변경 가능)
                    .maxSessionsPreventsLogin(false) // 동시 로그인 방지 설정 (false로 설정하여 기존 세션을 무효화하고 새로운 세션을 허용)
                    .expiredUrl("/login") // 세션 만료 시 이동할 URL 설정
                .and()
                    .invalidSessionUrl("/login") // 유효하지 않은 세션 시 이동할 URL 설정
                    .sessionFixation()
                    .migrateSession() // 세션 고정 보호 설정 (migrateSession() 메서드는 새로운 세션을 생성하여 고정 보호를 우회함)
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 세션 항상 생성 설정
                .and()
                .csrf()
                .disable();
    }

    /**
     * TODO:############Redis설정을 위한 추가##############
     * <p>
     * .sessionManagement()
     * .maximumSessions(1) // 동시 세션 수 설정 (원하는 값으로 변경 가능)
     * .maxSessionsPreventsLogin(false) // 동시 로그인 방지 설정 (false로 설정하여 기존 세션을 무효화하고 새로운 세션을 허용)
     * .expiredUrl("/login") // 세션 만료 시 이동할 URL 설정
     * .and()
     * .invalidSessionUrl("/login") // 유효하지 않은 세션 시 이동할 URL 설정
     * .sessionFixation().migrateSession() // 세션 고정 보호 설정 (migrateSession() 메서드는 새로운 세션을 생성하여 고정 보호를 우회함)
     * .and()
     * .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 세션 항상 생성 설정
     * .and()
     * .and()
     * <p>
     * <p>
     * .logout()
     * .invalidateHttpSession(true) // 로그아웃 시 세션 무효화
     * .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
     * .and()
     * .and()
     *
     * @Bean public HttpSessionEventPublisher httpSessionEventPublisher() {
     * return new HttpSessionEventPublisher();
     * }
     */

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        return new LoginSuccessHandler(accountService, redisTemplate);
    }

//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("a5e21da872b3a0125eba")
                .clientSecret("b8c6145f7a1ce9f670b31efd1c50e719b07541fb")
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration github) {
        return new InMemoryClientRegistrationRepository(github);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }
}
