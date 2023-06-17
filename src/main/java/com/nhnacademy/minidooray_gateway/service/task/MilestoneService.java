package com.nhnacademy.minidooray_gateway.service.task;

import com.nhnacademy.minidooray_gateway.dto.task.MilestoneCreateDto;
import com.nhnacademy.minidooray_gateway.dto.task.MilestoneDto;
import com.nhnacademy.minidooray_gateway.dto.task.TaskDto;
import com.nhnacademy.minidooray_gateway.utils.RestTemplateUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MilestoneService {
    private final RestTemplateUtils restTemplateUtils;

    public MilestoneService(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
        //Object response = restTemplateUtils.requestApi("task", "/projcets", "POST", projectCreateDto, 200, null);
    }

    public void createMilestone(MilestoneCreateDto milestoneCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/milestones", "POST", milestoneCreateDto, 200, null);
    }

    public String getMilestoneById(Long milestoneId) {
        String response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId, "GET", null, 200, MilestoneDto.class);
        return response;
    }

    public String getMilestoneByProjectId(Long projectId) {
        String response = restTemplateUtils.requestApi("task", "/milestones/" + projectId + "/milestones", "GET", null, 200, List.class);
        return response;
    }

    public void updateMilestoneById(Long milestoneId, MilestoneCreateDto milestoneCreateDto) {
        Object response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId, "PUT", milestoneCreateDto, 200, null);
    }

    public void deleteMilestoneById(Long milestoneId) {
        Object response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId, "DELETE", null, 200, null);
    }

    public void createMilestoneTask(Long milestoneId, Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId + "/tasks/" + taskId, "POST", null, 200, null);
    }

    public String getMilestoneByTaskId(Long taskId) {
        String response = restTemplateUtils.requestApi("task", "/tasks/" + taskId + "/milestones", "GET", null, 200, MilestoneDto.class);
        return response;
    }

    public String getTasksByMilestoneId(Long milestoneId) {
        String response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId + "/tasks", "GET", null, 200, List.class);
        return response;
    }

    public void deleteMilestoneTask(Long milestoneId, Long taskId) {
        Object response = restTemplateUtils.requestApi("task", "/milestones/" + milestoneId + "/tasks/" + taskId, "DELETE", null, 200, null);
    }
}
