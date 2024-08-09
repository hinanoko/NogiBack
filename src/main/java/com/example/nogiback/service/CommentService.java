package com.example.nogiback.service;


import com.example.nogiback.entity.Comment;
import com.example.nogiback.entity.CommentWithScore;
import com.example.nogiback.mapper.CommentMapper;
import com.example.nogiback.mapper.RankMapper;
import com.example.nogiback.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RankMapper rankMapper;  // 确保 rankMapper 通过 @Autowired 注入

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper){this.commentMapper = commentMapper;}

    public String addComment(Comment comment) {
        try {
            commentMapper.insertComment(comment);
            return "Comment added successfully!";
        } catch (Exception e) {
            // 记录异常日志，便于调试
            e.printStackTrace();
            return "Failed to add comment: " + e.getMessage();
        }
    }


    public List<CommentWithScore> getCommentsByMemberId(int memberId) {
        // 获取评论列表
        List<Comment> comments = commentMapper.findCommentsByMemberId(memberId);

        // 创建新的 CommentWithScore 列表
        List<CommentWithScore> commentsWithScore = new ArrayList<>();

        // 遍历每条评论
        for (Comment comment : comments) {
            // 根据评论的 customerName 和 memberId 去查找该用户对该成员的评分
            Integer memberMark = rankMapper.getMemberMark(comment.getCustomerId(), memberId);

            String customerName = userMapper.getUserNameById(comment.getCustomerId());

            // 如果找不到该用户的评分，就设为 0
            if (memberMark == null) {
                memberMark = 0;
            }

            // 创建 CommentWithScore 实体对象
            CommentWithScore commentWithScore = new CommentWithScore();
            commentWithScore.setCommentId(comment.getCommentId());
            commentWithScore.setCustomerId(comment.getCustomerId());
            commentWithScore.setMemberId(comment.getMemberId());
            commentWithScore.setContent(comment.getContent());
            commentWithScore.setDate(comment.getDate());
            commentWithScore.setScore(memberMark);
            commentWithScore.setCustomerName(customerName);

            // 添加到返回列表中
            commentsWithScore.add(commentWithScore);
        }

        return commentsWithScore;
    }


}
