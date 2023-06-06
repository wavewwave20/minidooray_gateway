package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private String userUUID;
    private String userId;
    private String password;
    private String userName;
    //#TODO:이메일도 필요하지 않나요?


}
