package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.ProjectDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserTaskDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final RestTemplateUtils restTemplateUtils;

    public UserService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
        //Object response = restTemplateUtils.requestApi("task", "/projcets", "POST", projectCreateDto, 200, null);
    }

    public void createUser(UserCreateDto userCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/users", "POST", userCreateDto, 200, null);
    }

    public void updateUserById(Long userId, UserCreateDto userCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/users/" + userId, "PUT", userCreateDto, 200, null);
    }

    public void deleteUserById(Long userId) {
        Object response = restTemplateUtils.requestApi("task", "/users/" + userId, "DELETE", null, 200, null);
    }

    public UserDto getUserById(Long userId) {
        Object response = restTemplateUtils.requestApi("task", "/users/" + userId, "GET", null, 200, UserDto.class);
        return (UserDto) response;
    }

    public List<UserDto> getAllUsers() {
        Object response = restTemplateUtils.requestApi("task", "/users", "GET", null, 200, List.class);
        return (List<UserDto>) response;
    }

    public List<ProjectDto> getAllProjects(String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/users/" + userUUID + "/projects", "GET", null, 200, List.class);
        return (List<ProjectDto>) response;
    }

    public List<UserTaskDto> getUserTaskByTaskId(Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/users", "GET", null, 200, List.class);
        return (List<UserTaskDto>) response;
    }
}
