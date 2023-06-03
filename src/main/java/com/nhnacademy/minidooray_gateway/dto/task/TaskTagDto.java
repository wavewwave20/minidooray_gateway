package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TaskTagDto {
    //#TODO:Tag id 불필요한지 확인부탁드립니다.
    private Long tagId;
    private String tagName;
    private Long taskId;
}
