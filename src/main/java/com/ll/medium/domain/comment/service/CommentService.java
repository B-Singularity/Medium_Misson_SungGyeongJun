package com.ll.medium.domain.comment.service;

import com.ll.medium.domain.comment.dto.AddCommentRequest;
import com.ll.medium.domain.comment.dto.UpdateCommentRequest;
import com.ll.medium.domain.comment.entity.Comment;
import com.ll.medium.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;


    public Comment create(AddCommentRequest addCommentRequest) {
        return commentRepository.save(addCommentRequest.toEntity());
    }

    public Comment update(long id, UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findCommentById(id);

        Comment comment = optionalComment.orElseThrow(() -> new IllegalArgumentException("Comment not found for id: " + id));

        comment.update(updateCommentRequest.getBody());

        return comment;
    }

    public Comment delete(long id) {
        Comment target = commentRepository.findCommentById(id).orElse(null);
        if (target == null) {
            return null;
        }
        commentRepository.delete(target);
        return target;
    }
}
