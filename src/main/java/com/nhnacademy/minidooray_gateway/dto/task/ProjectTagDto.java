package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProjectTagDto {
    private Long tagId;
    private String tagName;
    private Long projectId;
}
