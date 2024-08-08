package com.example.nogiback.controller;

import com.example.nogiback.entity.MemberScoreAndRank;
import com.example.nogiback.entity.RatingRequest;
import com.example.nogiback.service.RankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Resource
    private RankService rankService;

    @GetMapping("/member")
    public ResponseEntity<MemberScoreAndRank> getMemberScoresAndRank(@RequestParam int memberId) {
        try {
            MemberScoreAndRank result = rankService.getMemberScoresAndRank(memberId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/rate")
    public ResponseEntity<String> addRating(@RequestBody RatingRequest ratingRequest) {
        try {
            rankService.addRating(ratingRequest.getCustomerId(), ratingRequest.getMemberId(), ratingRequest.getMemberMark());
            return ResponseEntity.ok("Rating added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add rating: " + e.getMessage());
        }
    }
}
