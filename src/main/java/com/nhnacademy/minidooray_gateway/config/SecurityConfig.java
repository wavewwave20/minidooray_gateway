package com.nhnacademy.minidooray_gateway.config;


import com.nhnacademy.minidooray_gateway.handler.LoginSuccessHandler;
import com.nhnacademy.minidooray_gateway.service.CustomUserDetailsService;
import com.nhnacademy.minidooray_gateway.service.UserInfoBeanForRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserInfoBeanForRedis userInfoBeanForRedis;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**", "/").authenticated()
//                .antMatchers("/auth/login").authenticated()
                .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .usernameParameter("userId")
                    .passwordParameter("password")
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/login")
                    .successHandler(loginSuccessHandler(redisTemplate, userInfoBeanForRedis))
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/auth/login")//커밋
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")

                .and()
                .oauth2Login()
                    .loginPage("/oauth2/authorization/github")
                    .defaultSuccessUrl("/")
                .and()
                    .headers()
                    .frameOptions()
                    .disable()
                .and()
                    .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login")
//                .and()
//                    .invalidSessionUrl("/login") // 유효하지 않은 세션 시 이동할 URL 설정
//                    .sessionFixation()
//                    .migrateSession() // 세션 고정 보호 설정 (migrateSession() 메서드는 새로운 세션을 생성하여 고정 보호를 우회함)
//                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 세션 항상 생성 설정
                .and().and()
                .csrf().disable();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, String> redisTemplate, UserInfoBeanForRedis userInfoBeanForRedis) {
        return new LoginSuccessHandler(redisTemplate, userInfoBeanForRedis);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


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
