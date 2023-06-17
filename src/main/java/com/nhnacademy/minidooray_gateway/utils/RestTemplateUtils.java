package com.nhnacademy.minidooray_gateway.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Objects;

@Component
@Slf4j
public class RestTemplateUtils {

    private final AccountProperties accountProperties;
    private final RestTemplate restTemplate;

    private String accountIp;

    private int accountPort;

    private String taskIp;

    private int taskPort;

    public RestTemplateUtils(AccountProperties accountProperties, RestTemplate restTemplate) {
        this.accountProperties = accountProperties;
        this.restTemplate = restTemplate;
        this.accountIp = accountProperties.getAccountIp();
        this.accountPort = accountProperties.getAccountPort();
        this.taskIp = accountProperties.getTaskIp();
        this.taskPort = accountProperties.getTaskPort();
    }

    public Object requestApi(String api ,String url, String method, Object requestBody, int status, Class<?> responseBody) {
        String requestUrl = "";

        if(api.equals("account")) {
            requestUrl = "http://" + accountIp + ":" + accountPort + "/api/account" + url;
        } else if(api.equals("task")) {
            requestUrl = "http://" + taskIp + ":" + taskPort + "/api/task" + url;
        } else {
            throw new UsernameNotFoundException("no");
        }

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(requestUrl, httpMethod, entity, Object.class);
            if (responseBody != null) {
                if((status == 200) && (response.getStatusCode() == HttpStatus.OK)) {
                    return responseOKMapping(response, responseBody);
                }
                if((status == 201) && (response.getStatusCode() == HttpStatus.CREATED)) {
                    return responseCreatedMapping(response, responseBody);
                }
            } else {
                if(status == 200) {
                    return "OK";
                }
                if(status == 201) {
                    return "CREATED";
                }
            }

        } catch (RestClientException e) {
            log.error("RestClientException", e);
        }
        throw new UsernameNotFoundException("no");
    }

    public Object responseCreatedMapping(ResponseEntity<Object> responseEntity, Class<?> clazz) {
        if (Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.CREATED){
            Object responseBody = responseEntity.getBody();
            if (responseBody instanceof LinkedHashMap) {
                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) responseBody;

                ObjectMapper mapper = new ObjectMapper();
                return mapper.convertValue(map, clazz);

            } else {
                log.error("Response body is not in the json format");
            }
        }

        throw new UsernameNotFoundException("no");
    }

    public Object responseOKMapping(ResponseEntity<Object> responseEntity, Class<?> clazz) {
        if (Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.OK){
            Object responseBody = responseEntity.getBody();
            if (responseBody instanceof LinkedHashMap) {
                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) responseBody;

                ObjectMapper mapper = new ObjectMapper();
                return mapper.convertValue(map, clazz);

            } else {
                log.error("Response body is not in the json format");
            }
        }
        throw new UsernameNotFoundException("no");
    }




}
