package com.nhnacademy.minidooray_gateway.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class UserInfoBeanForRedis{
    private String userEmail;

    private String userNickname;

    private String userUUId;

}
