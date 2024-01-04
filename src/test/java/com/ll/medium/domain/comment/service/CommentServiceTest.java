package com.ll.medium.domain.comment.service;

import com.ll.medium.domain.comment.dto.AddCommentRequest;
import com.ll.medium.domain.comment.entity.Comment;
import com.ll.medium.domain.comment.repository.CommentRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @Test
    @DisplayName("commentCreateTest")
    public void test1() {
        // AddCommentRequest 객체 생성
        AddCommentRequest addCommentRequest = AddCommentRequest.builder()
                .articleId(1L)
                .body("comment1")
                .build();

        // CommentRepository의 save 메서드가 호출되었는지 확인
        when(commentRepository.save(any(Comment.class))).thenReturn(new Comment());

        // 테스트 대상 메서드 실행
        Long commentId = commentService.create(addCommentRequest).getId();


    }
}
