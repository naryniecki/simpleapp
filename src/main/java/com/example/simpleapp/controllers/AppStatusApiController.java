package com.example.simpleapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppStatusApiController {
    @PostMapping("/install")
    public ResponseEntity install() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/uninstall")
    public ResponseEntity uninstall() {
        return ResponseEntity.ok("");
    }

    @PostMapping("/configure")
    public ResponseEntity configure() {
        return ResponseEntity.ok("");
    }

    @GetMapping("/status")
    public ResponseEntity status() {
        return ResponseEntity.ok("OK");
    }
}

