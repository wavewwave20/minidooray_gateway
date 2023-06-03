package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
//#TODO:UserDto에 필요한 필드 추가해주세요.
@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private String userUUID;
    private String userId;
    private String password;
    private String userName;

}
