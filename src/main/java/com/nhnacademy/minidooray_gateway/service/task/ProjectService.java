package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.ProjectCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.ProjectDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final RestTemplateUtils restTemplateUtils;

    public ProjectService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
    }

    public void createProject(ProjectCreateDto projectCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/projects", "POST", projectCreateDto, 200, null);
    }

    public String getProjectById(Long projectId) {
        String response = restTemplateUtils.requestApi("task", "/projects/" + projectId, "GET", null, 200, ProjectDto.class);
        return response;
    }

    public void updateProjectById(Long projectId, ProjectDto projectDto) {
        Object response = restTemplateUtils.requestApi("task", "/projects/" + projectId, "PUT", projectDto, 200, null);
    }

    public void deleteProjectById(Long projectId) {
        Object response = restTemplateUtils.requestApi("task", "/projects/" + projectId, "DELETE", null, 200, null);
    }

    public void createProjectUser(Long projectId, String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/projects/" + projectId + "/users/" + userUUID, "POST", null, 200, null);
    }

    public void deleteProjectUser(Long projectId, String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/projects/" + projectId + "/users/" + userUUID, "DELETE", null, 200, null);
    }

    public void deleteProjectUserByProjectId(Long projectId) {
        Object response = restTemplateUtils.requestApi("task", "/projects/" + projectId + "/users", "DELETE", null, 200, null);
    }

    public String getProjectUserAll(Long projectId) {
        String response = restTemplateUtils.requestApi("task", "/projects/" + projectId + "/users", "GET", null, 200, List.class);
        return response;
    }




}
