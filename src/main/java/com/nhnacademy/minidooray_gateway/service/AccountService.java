package com.nhnacademy.minidooray_gateway.service;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.dto.account.UserLoginResponseDto;
import com.nhnacademy.minidooray_gateway.dto.account.UserRegisterDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AccountService {
    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;
    private final UserInfoBeanForRedis userInfoBeanForRedis;

    private final PasswordEncoder passwordEncoder;


    //#TODO:RestTemplate 수정요
    public UserDetails login(String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/accountapi/login" + "/" + userId;

        ResponseEntity<UserLoginResponseDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );

        if(Objects.requireNonNull(responseEntity.getBody()).getUserId().equals(userId)) {

            UserLoginResponseDto userInfo = responseEntity.getBody();

            //TODO: userUUID, userNickname 인메모리 저장하여 successHandler에서 사용 필
//            ThreadLocal.set(userInfo.getUserNickname(), userInfo.getUserUUID());

//            userInfoBeanForRedis.setUserUUId(responseEntity.getBody().getUserUUID());
//            userInfoBeanForRedis.setUserNickname(responseEntity.getBody().getUserNickname());
//            userInfoBeanForRedis.setUserEmail(responseEntity.getBody().getUserEmail());


            return new User(Objects.requireNonNull(userInfo).getUserId(), userInfo.getUserPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
        }

        throw new UsernameNotFoundException(userId + "not found");
    }

    public void logout() {
    }


    public void register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserRegisterDto> httpEntity = new HttpEntity<>(userRegisterDto, httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/accountapi/signup";

        restTemplate.exchange( url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {});

    }

    //@Getter
    //@Setter
    //public class LoginResponseDto {
    //    private String userUUID;
    //
    //    private String userId;
    //
    //    private String userNickname;
    //}

}
