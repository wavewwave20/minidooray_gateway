package com.nhnacademy.minidooray_gateway.dto.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {

    private String userId;

    private String userNickname;

    private String userEmail;
}
