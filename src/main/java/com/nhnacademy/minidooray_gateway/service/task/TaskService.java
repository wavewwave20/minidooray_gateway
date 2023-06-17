package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.TaskCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.TaskDto;
import com.nhnacademy.minidooray_gateway.dto.task.UserTaskDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final RestTemplateUtils restTemplateUtils;


    public TaskService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
        //Object response = restTemplateUtils.requestApi("task", "/projcets", "POST", projectCreateDto, 200, null);
    }

    public void createTask(TaskCreateDto taskCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/tasks", "POST", taskCreateDto, 200, null);
    }

    public TaskDto getTaskById(Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId, "GET", null, 200, TaskDto.class);
        return (TaskDto) response;
    }

    public List<TaskDto> getTaskByProjectId(Long projectId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + projectId + "/tasks", "GET", null, 200, List.class);
        return (List<TaskDto>) response;
    }

    public List<TaskDto> getTaskByAdminUserUUID(String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + userUUID + "/admin/tasks", "GET", null, 200, List.class);
        return (List<TaskDto>) response;
    }

    public void updateTaskById(Long taskId, TaskDto taskDto) {
        restTemplateUtils.requestApi("task", "/tasks/" + taskId, "PUT", taskDto, 200, null);
    }

    public void deleteTaskById(Long taskId) {
        restTemplateUtils.requestApi("task", "/tasks/" + taskId, "DELETE", null, 200, null);
    }

    public void createTaskTag(Long taskId, Long tagId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/tags/" + tagId, "POST", null, 200, null);
    }

    public List<TaskDto> getTasksByTag(Long tagId) {
        Object response = restTemplateUtils.requestApi("task", "/tags/" + tagId + "/tasks", "GET", null, 200, List.class);
        return (List<TaskDto>) response;
    }

    public void deleteTaskTag(Long taskId, Long tagId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/tags/" + tagId, "DELETE", null, 200, null);
    }

    public void createUserTask(Long taskId, String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/users/" + userUUID, "POST", null, 200, null);
    }

    public List<UserTaskDto> getUserTaskByTaskId(Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/users", "GET", null, 200, List.class);
        return (List<UserTaskDto>) response;
    }

    public void deleteUserTask(Long taskId, String userUUID) {
        Object response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/users/" + userUUID, "DELETE", null, 200, null);
    }
}
