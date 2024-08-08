package com.example.nogiback.controller;

import com.example.nogiback.entity.Comment;
import com.example.nogiback.entity.CommentWithScore;
import com.example.nogiback.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public String addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }


    @GetMapping("/member")
    public List<CommentWithScore> getCommentsByMemberId(@RequestParam int memberId) {
        return commentService.getCommentsByMemberId(memberId);
    }


}
