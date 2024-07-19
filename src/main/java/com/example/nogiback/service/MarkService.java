package com.example.nogiback.service;

import com.example.nogiback.entity.MarkMember;
import com.example.nogiback.mapper.MarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarkService {
    private MarkMapper markMapper;

    @Autowired
    public MarkService(MarkMapper markMapper){
        this.markMapper = markMapper;
    }

    public String updateMemberMarks(int memberId, int score) {
        try {
            if (score > 0) {
                markMapper.updateMemberPosiMark(memberId, score);
            } else if (score < 0) {
                markMapper.updateMemberNogiMark(memberId, -score); // Ensure score is positive for update
            } else {
                return "Score must be non-zero.";
            }
            return "Marks updated successfully.";
        } catch (Exception e) {
            return "Failed to update marks: " + e.getMessage();
        }
    }

    public List<MarkMember> getAllMemberMarks() {
        return markMapper.getAllMemberMarks();
    }
}
