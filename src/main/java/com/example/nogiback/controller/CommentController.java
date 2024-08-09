package com.example.nogiback.controller;

import com.example.nogiback.entity.Comment;
import com.example.nogiback.entity.CommentWithScore;
import com.example.nogiback.service.CommentService;
import com.example.nogiback.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String addComment(@RequestBody Comment comment, @RequestHeader("Authorization") String token) {
        if (JwtUtil.checkToken(token)) {
            return commentService.addComment(comment);
        }else {
            return ("Invalid or missing token.");
        }
    }


    @GetMapping("/member")
    public List<CommentWithScore> getCommentsByMemberId(@RequestParam int memberId) {
        return commentService.getCommentsByMemberId(memberId);
    }


}
