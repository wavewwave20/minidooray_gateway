package com.nhnacademy.minidooray_gateway.service;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.dto.account.UserLoginDto;
import com.nhnacademy.minidooray_gateway.dto.account.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;

    public UserLoginDto login(UserLoginDto userLoginDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp() + ":" + accountProperties.getAccountIp() + "/accountapi/login";
        ResponseEntity<UserLoginDto> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();

    }

    public void logout() {
    }


    public void register(UserRegisterDto userRegisterDto) {
    }


}
