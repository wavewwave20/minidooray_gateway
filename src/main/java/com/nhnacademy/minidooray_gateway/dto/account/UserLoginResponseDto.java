package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserLoginResponseDto {

    private String userUUID;

    private String userId;

    private String userNickname;
}
