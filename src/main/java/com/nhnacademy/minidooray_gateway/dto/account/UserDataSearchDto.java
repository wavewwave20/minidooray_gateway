package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDataSearchDto {
    private String userId;
    private String userNickName;
    private String userEmail;
}
