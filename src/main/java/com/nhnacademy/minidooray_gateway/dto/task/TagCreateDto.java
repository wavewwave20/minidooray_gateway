package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TagCreateDto {
    private String tagName;
    private Long projectId;
}
