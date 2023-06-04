package com.nhnacademy.minidooray_gateway.service;

import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import com.nhnacademy.minidooray_gateway.dto.account.UserLoginDto;
import com.nhnacademy.minidooray_gateway.dto.account.UserLoginResponseDto;
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

    //#TODO:RestTemplate 수정요망
    public UserLoginResponseDto login(UserLoginDto userLoginDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<UserLoginDto> httpEntity = new HttpEntity<>(userLoginDto, httpHeaders);
        String url = "http://" + accountProperties.getAccountIp() + ":" + accountProperties.getAccountPort() + "/accountapi/login";
        ResponseEntity<UserLoginResponseDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {}
        );
        return responseEntity.getBody();
    }

    public void logout() {
    }


    public void register(UserRegisterDto userRegisterDto) {
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
