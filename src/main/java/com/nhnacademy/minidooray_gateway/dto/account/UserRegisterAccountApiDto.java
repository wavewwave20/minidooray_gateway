package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRegisterAccountApiDto {

    private String userUUID;
    private String userId;
    private String userNickName;
    private String userEmail;
}
