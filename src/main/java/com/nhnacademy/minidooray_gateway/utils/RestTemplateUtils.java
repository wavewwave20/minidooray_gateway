package com.nhnacademy.minidooray_gateway.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.minidooray_gateway.config.AccountProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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

    public String requestApi(String api ,String url, String method, Object requestBody, int status, Class<?> responseBody) {
        String requestUrl = "";

        if(api.equals("account")) {
            requestUrl = "http://" + accountIp + ":" + accountPort + "/api/account" + url;
        } else if(api.equals("task")) {
            requestUrl = "http://" + taskIp + ":" + taskPort + "/api/task" + url;
        } else {
            throw new UsernameNotFoundException("no");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        HttpEntity<Object> entity = new HttpEntity<>(requestBody, headers);

        if (responseBody == null) {

            restTemplate.exchange(requestUrl, httpMethod, entity, Void.class);
            return "OK";
        }


        try {
            ResponseEntity<Object> response = restTemplate.exchange(requestUrl, httpMethod, entity, Object.class);
            if (responseBody != null) {
                if((status == 200) && (response.getStatusCode() == HttpStatus.OK)) {
                    return responseOKMapping(response);
                }
                if((status == 201) && (response.getStatusCode() == HttpStatus.CREATED)) {
                    return responseCreatedMapping(response);
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

    public String responseCreatedMapping(ResponseEntity<Object> responseEntity) {
        if (Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.CREATED){
            Object responseBody = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();

            try {
                if (responseBody instanceof LinkedHashMap) {
                    LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) responseBody;
                    return mapper.writeValueAsString(map);
                } else if (responseBody instanceof List) {
                    return mapper.writeValueAsString(responseBody);
                } else {
                    log.error("Response body is not in the expected format");
                }
            } catch (JsonProcessingException e) {
                log.error("Failed to convert response body to JSON string", e);
            }
        }
        throw new UsernameNotFoundException("no");
    }

    public String responseOKMapping(ResponseEntity<Object> responseEntity) {
        if (Objects.nonNull(responseEntity) && responseEntity.getStatusCode() == HttpStatus.OK){
            Object responseBody = responseEntity.getBody();
            ObjectMapper mapper = new ObjectMapper();

            try {
                if (responseBody instanceof LinkedHashMap) {
                    LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) responseBody;
                    return mapper.writeValueAsString(map);
                } else if (responseBody instanceof List) {
                    return mapper.writeValueAsString(responseBody);
                } else {
                    log.error("Response body is not in the expected format");
                }
            } catch (JsonProcessingException e) {
                log.error("Failed to convert response body to JSON string", e);
            }
        }
        throw new UsernameNotFoundException("no");
    }





}
