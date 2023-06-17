package com.nhnacademy.minidooray_gateway.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.dto.account.*;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;


@Service
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;
    private final UserInfoBeanForRedis userInfoBeanForRedis;
    private final PasswordEncoder passwordEncoder;

    private final RestTemplateUtils restTemplateUtils;


    public UserDetails login(String userId) {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserId(userId);

        Object response = restTemplateUtils.requestApi("account", "/login" + "/" + userId, "GET", userLoginDto, 200, UserLoginResponseDto.class);
        if(response == null) {
            throw new UsernameNotFoundException(userId + "not found");
        }
        UserLoginResponseDto userInfo = (UserLoginResponseDto) response;

        userInfoBeanForRedis.setUserUUId(userInfo.getUserUUID());
        userInfoBeanForRedis.setUserNickname(userInfo.getUserNickname());
        userInfoBeanForRedis.setUserEmail(userInfo.getUserEmail());

        return new User(Objects.requireNonNull(userInfo).getUserId(), userInfo.getUserPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }

    public void logout() {
        //#TODO 시큐리티가 해주는지 확인하기(/logout)
    }

    public void register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Object response = restTemplateUtils.requestApi("account", "/users", "POST", userRegisterDto, 201, UserRegisterAccountApiDto.class);
        if(response == null) {
            throw new UsernameNotFoundException("no");
        }
        UserRegisterAccountApiDto userInfo = (UserRegisterAccountApiDto) response;

        restTemplateUtils.requestApi("task", "/users", "POST" ,userInfo,200,null);

    }


    public void deleteUser(String userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/account/users" + "/" + userId;
        restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, new ParameterizedTypeReference<>() {
        });
    }

    public void updateUser(UserRegisterDto userRegisterDto, String userUUID) {
        userRegisterDto.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserRegisterDto> httpEntity = new HttpEntity<>(userRegisterDto, httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/account/users" + "/" + userUUID;

        ResponseEntity<UserUpdateDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        if (Objects.requireNonNull(responseEntity.getBody()).getUserId().equals(userRegisterDto.getUserId())) {
            UserUpdateDto userUpdateDto = responseEntity.getBody();
            updateUserTaskApi(userUpdateDto, userUUID);
        }
        throw new UsernameNotFoundException("no");
    }
    public void updateUserTaskApi(UserUpdateDto userUpdateDto, String userUUID) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserUpdateDto> httpEntity = new HttpEntity<>(userUpdateDto, httpHeaders);
        String url = "http://" + accountProperties.getTaskIp()
                + ":" + accountProperties.getTaskPort() + "/api/task/users/" + userUUID;
        restTemplate.exchange(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<>() {
        });
    }

}
