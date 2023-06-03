package com.nhnacademy.minidooray_gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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

import javax.annotation.PostConstruct;


/**
 * Security 설정
 * #TODO: Security 설정 완료할것.
 * #TODO: .exceptionHandling() 구현
 */



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Lazy
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    @Lazy
    private OAuth2AuthorizedClientService authorizedClientService;


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
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository)
                .authorizedClientService(authorizedClientService)
                .loginPage("/oauth2/authorization/github")
                .defaultSuccessUrl("/")
                .and()
                .sessionManagement()
                .sessionFixation()
                .none()
                .and()
                .headers()
                .frameOptions().disable()
                .and()
                .csrf()
                .disable()
                .build();
    }

    //#TODO: Password관련
//    @Bean
//    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(customUserDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return authenticationProvider;
//    }

    //#TODO: PasswordEncoder 구현할것.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //#TODO: OAuth구현 InMemoryOAuth2AuthorizedClientService를 사용하지 않고, DB를 사용하여 구현할것.
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
