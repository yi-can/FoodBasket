package com.voco_task.service;

import com.voco_task.dto.request.CommentRequestDto;
import com.voco_task.dto.response.CommentResponseDto;
import com.voco_task.repository.ICommentRepository;
import com.voco_task.repository.entity.Comment;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment, String> {

    private final ICommentRepository repository;

    public CommentService(ICommentRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public CommentResponseDto createComment(String userId, String restaurantId, CommentRequestDto dto) {
        Comment comment = Comment.builder()
                .userId(userId)
                .restaurantId(restaurantId)
                .restaurantName(dto.getRestaurantName())
                .comment(dto.getComment())
                .userName(dto.getUserName())
                .build();
        comment = repository.save(comment);

        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUserId())
                .restaurantId(comment.getRestaurantId())
                .restaurantName((comment.getRestaurantName()))
                .comment(comment.getComment())
                .userName(comment.getUserName())
                .build();

    }

    public void deleteComment(String commentId) {
        Optional<Comment> optionalComment = repository.findById(commentId);
        if (optionalComment.isPresent()) {
            repository.delete(optionalComment.get());
            System.out.println(commentId + " numaralı yorum başarıyla silindi.");
        } else {
            System.out.println(commentId + " numaralı yorum bulunamadı.");
        }
    }



}