package com.ll.medium.domain.article.dto;

import com.ll.medium.domain.article.entity.Article;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class AddArticleRequest {
    private String title;

    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
