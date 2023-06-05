package com.nhnacademy.minidooray_gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class AccountProperties {

    @Value("${account.ip}")
    private String accountIp;
    @Value("${account.port}")
    private int accountPort;


}
