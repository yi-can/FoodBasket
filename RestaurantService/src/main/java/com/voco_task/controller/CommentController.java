package com.voco_task.controller;


import com.voco_task.dto.request.CommentRequestDto;
import com.voco_task.dto.response.CommentResponseDto;
import com.voco_task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.voco_task.constant.Endpoints.*;



@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
@CrossOrigin("*")

public class CommentController {

    private final CommentService commentService;

    @PostMapping(CREATE_COMMENT)    // ("/create_comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable String userId, @PathVariable String restaurantId, @RequestBody CommentRequestDto dto) {

        return ResponseEntity.ok(commentService.createComment(userId, restaurantId, dto));
    }

    @DeleteMapping(DELETE_COMMENT)
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Yorum Başarıyla silindi.");
    }


}

