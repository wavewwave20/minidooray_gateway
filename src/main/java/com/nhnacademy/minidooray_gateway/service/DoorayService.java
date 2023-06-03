package com.nhnacademy.minidooray_gateway.service;

import com.nhnacademy.minidooray_gateway.dto.task.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorayService {

    //#TODO: 실제 Account 및 서비스들과 연동 
    

    public ProjectDto getProjectDetail(Long id) {
        return null;
    }

    public List<ProjectDto> getAllProjectList() {
        return null;
    }

    public void saveProject(ProjectDto projectDto) {
    }

    public void updateProject(Long id, ProjectDto projectDto) {
    }

    public void deleteProject(Long id) {
    }
}
