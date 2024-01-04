package com.ll.medium.domain.comment.api;

import com.ll.medium.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @GetMapping("/")
}
