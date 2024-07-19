package com.example.nogiback.controller;

import com.example.nogiback.entity.MarkMember;
import com.example.nogiback.service.MarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @Resource
    private MarkService markService;

    @GetMapping
    public ResponseEntity<?> getAllMarks(){
        try {
            List<MarkMember> marks = markService.getAllMemberMarks();
            if (marks.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Catch successful");
            response.put("body", marks);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching marks: " + e.getMessage());
        }
    }

}
