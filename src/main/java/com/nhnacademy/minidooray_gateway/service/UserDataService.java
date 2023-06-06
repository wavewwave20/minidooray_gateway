package com.nhnacademy.minidooray_gateway.service;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.dto.account.UserDataSearchDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class UserDataService {
    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;


    public List<UserDataSearchDto> getAllUserData() {
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<List<UserDataSearchDto>> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/userdata/";

        ResponseEntity<List<UserDataSearchDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();
    }

    public UserDataSearchDto getUserDataDetail(@RequestBody String userId) {
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserDataSearchDto> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/userdata/" + userId;

        ResponseEntity<UserDataSearchDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();
    }

    public String putUserData(@RequestBody String userId, @RequestBody UserDataSearchDto userDataSearchDto) {
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserDataSearchDto> httpEntity = new HttpEntity<>(userDataSearchDto, httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/userdata/" + userId;

        ResponseEntity<UserDataSearchDto> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody().getUserId();


    }

    public String deleteUserData(@RequestBody String userId) {
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        String url = "http://" + accountProperties.getAccountIp()
                + ":" + accountProperties.getAccountPort() + "/api/userdata/" + userId;

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, new ParameterizedTypeReference<>() {
        });
        return responseEntity.getBody();

    }
}
