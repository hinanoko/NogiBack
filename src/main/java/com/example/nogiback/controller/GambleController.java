package com.example.nogiback.controller;


import com.example.nogiback.entity.Gamble;
import com.example.nogiback.service.GambleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gamble")
public class GambleController {

    @Resource
    private GambleService gambleService;

    @PostMapping
    public ResponseEntity<?> addNewGambleRecord(
            @RequestBody Gamble gamble,
            @RequestHeader("Authorization") String token) {
        System.out.println(token);
        try {
            String response = gambleService.addGamble(gamble, token);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", response);
            return ResponseEntity.ok().body(responseBody);
        } catch (Exception e) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "Gamble addition failed");
            responseBody.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }
    }


    @GetMapping("/byName")
    public ResponseEntity<Map<String, Object>> getGambleByName(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> gambleInformation = gambleService.selectGambleByName(name);

        String serviceMessage = (String) gambleInformation.get("serviceMessage");
        if ("success".equals(serviceMessage)) {
            System.out.println(gambleInformation);
            response.put("message", "success");
            response.put("data", gambleInformation);
        } else if ("fail to find".equals(serviceMessage)) {
            response.put("message", "fail to find");
        } else if ("error".equals(serviceMessage)) {
            response.put("message", "error");
            response.put("error", gambleInformation.get("error"));
        }

        return ResponseEntity.ok(response);
    }
}
