package com.ll.medium.domain.comment.dto;

import com.ll.medium.domain.comment.entity.Comment;
import com.ll.medium.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddCommentRequest {
    private Long id;
    private Long articleId;
    private User commentor;
    private String body;

    public Comment toEntity() {
        return Comment.builder()
                .commentor(commentor)
                .body(body)
                .build();
    }
}
