package com.example.test12.controller;

import com.example.test12.dto.MessageDto;
import com.example.test12.service.ReceiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")

public class ReceiveController {
    private final ReceiveService service;

    @GetMapping("/q1/message")
    public ResponseEntity<String> send(){
        final MessageDto dto = service.receive1();
        return ResponseEntity.ok(String.valueOf(dto.getId()));
    }
    @GetMapping("/q2/message")
    public ResponseEntity<String> scheduledSend(){
        final MessageDto dto = service.receive2();
        return ResponseEntity.ok(String.valueOf(dto.getId()));
    }
}

