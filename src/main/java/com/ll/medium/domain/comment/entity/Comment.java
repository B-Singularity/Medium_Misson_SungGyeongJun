package com.ll.medium.domain.comment.entity;

import com.ll.medium.domain.article.entity.Article;
import com.ll.medium.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private User commentor;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
    @Column(columnDefinition = "TEXT")
    private String body;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Builder
    public void update(String body) {
        this.body = body;
    }
}
