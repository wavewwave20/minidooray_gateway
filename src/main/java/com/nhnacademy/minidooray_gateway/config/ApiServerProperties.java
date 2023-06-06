package com.nhnacademy.minidooray_gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApiServerProperties {

    @Value("${api.account.ip}")
    private String accountIp;
    @Value("${api.account.port}")
    private int accountPort;

    @Value("${api.task.ip}")
    private String taskIp;
    @Value("${api.task.port}")
    private int taskPort;


}
