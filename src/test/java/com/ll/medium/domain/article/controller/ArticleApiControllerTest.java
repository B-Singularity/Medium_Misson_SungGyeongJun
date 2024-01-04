package com.ll.medium.domain.article.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.medium.domain.article.api.ArticleApiController;
import com.ll.medium.domain.article.dto.AddArticleRequest;
import com.ll.medium.domain.article.entity.Article;
import com.ll.medium.domain.article.service.ArticleService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ArticleApiController.class)
public class ArticleApiControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Test
    @DisplayName("createArticle: 블로그 글 생성 성공한다.")
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void addArticle() throws Exception {
        // given
        AddArticleRequest requestForm = AddArticleRequest.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        Article createdArticle = Article.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        given(articleService.create(any(AddArticleRequest.class))).willReturn(createdArticle);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestForm))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // CSRF 토큰 추가
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Test Content")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

