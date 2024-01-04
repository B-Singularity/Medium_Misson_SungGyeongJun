package com.ll.medium.domain.comment.repository;

import com.ll.medium.domain.article.dto.AddArticleRequest;
import com.ll.medium.domain.article.entity.Article;
import com.ll.medium.domain.article.service.ArticleService;
import com.ll.medium.domain.comment.entity.Comment;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("findByArticleId")
    public void findByArticleId() {
        AddArticleRequest addArticleRequest = AddArticleRequest.builder()
                .title("test1")
                .content("content1")
                .build();
        Article article = articleService.create(addArticleRequest);
        Long articleId = article.getId();

        Comment comment = Comment.builder()
                .id(articleId) // 관련 Article ID 설정
                .body("comment1")
                .build();

        Comment savedComment = commentRepository.save(comment);
        Long commentId = savedComment.getId();

        // When
        Optional<Comment> foundComment = commentRepository.findCommentById(commentId);

        // Then
        assertThat(foundComment).isPresent();
        assertThat(foundComment.get().getBody()).isEqualTo("comment1");
    }

}
