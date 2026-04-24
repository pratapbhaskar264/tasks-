package com.aditya.socialguard.controller;

import com.aditya.socialguard.entity.Comment;
import com.aditya.socialguard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment addComment(@PathVariable("postId") Long postId,
                              @RequestBody Comment comment) {

        comment.setPostId(postId);
        return commentService.addComment(comment);
    }
}