package com.voco_task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    private String commentId;
    private String userId;
    private String restaurantId;
    private String restaurantName;
    private String comment;
    private String userName;
}
