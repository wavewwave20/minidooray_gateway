package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private String userUUID;
    private String userId;
    private String userNickName;
    private String userEmail;
}
