package com.nhnacademy.minidooray_gateway.dto.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentDto {
    private Long commentId;
    private String commentContent;
    private String commentCreationDate;
    private String userId;
    private String taskId;
}
