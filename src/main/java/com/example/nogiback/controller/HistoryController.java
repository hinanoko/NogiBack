package com.example.nogiback.controller;



import com.example.nogiback.entity.BlogCount;
import com.example.nogiback.entity.BlogSummary;
import com.example.nogiback.entity.History;
import com.example.nogiback.service.HistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @PostMapping
    public void gotNewBlog(@RequestBody History blogPost, @RequestHeader("Authorization") String token) {
        System.out.println(blogPost);

        // 转换 blogIdolId
        String originalIdolId = blogPost.getBlogIdolId();
        if (originalIdolId != null && originalIdolId.startsWith("Idol ")) {
            String numberPart = originalIdolId.substring(5); // 提取数字部分
            try {
                int idolNumber = Integer.parseInt(numberPart);
                String formattedIdolId = String.format("%04d", idolNumber); // 格式化为4位数字
                blogPost.setBlogIdolId(formattedIdolId);
            } catch (NumberFormatException e) {
                // 如果解析失败，保留原始值
                System.out.println("Failed to parse idol number: " + originalIdolId);
            }
        }

        historyService.addHistoryBlog(blogPost, token);

    }

    @GetMapping("/count")
    public List<BlogCount> getBlogCounts() {
        return historyService.getBlogCounts();
    }

    @GetMapping("/summary")
    public ResponseEntity<List<BlogSummary>> getBlogSummariesByIdolId(@RequestParam String idolId) {
        List<BlogSummary> summaries = historyService.getBlogSummariesByIdolId(idolId);
        return ResponseEntity.ok(summaries);
    }

}
