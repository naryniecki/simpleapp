package com.example.simpleapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppStatusApiController {
    private List<String> parameters = new ArrayList<>();

    @PostMapping("/install")
    public ResponseEntity install(@RequestParam("tenantId") String tenantId,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("siteId") String siteId,
                                  @RequestParam("siteName") String siteName,
                                  @RequestParam("appId") String appId,
                                  @RequestParam("oauth_consumer_key") String oauth_consumer_key
    ) {
        parameters.add(tenantId);
        parameters.add(userId);
        parameters.add(userName);
        parameters.add(siteId);
        parameters.add(siteName);
        parameters.add(appId);
        parameters.add(oauth_consumer_key);
        return ResponseEntity.ok(tenantId);
    }

    @PostMapping("/uninstall")
    public ResponseEntity uninstall(@RequestParam("tenantId") String tenantId,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("siteId") String siteId,
                                    @RequestParam("siteName") String siteName,
                                    @RequestParam("appId") String appId,
                                    @RequestParam("oauth_consumer_key") String oauth_consumer_key
    ) {
        parameters.removeAll(parameters);
        parameters.add(oauth_consumer_key);
        return ResponseEntity.ok(parameters);
    }

    @PostMapping("/configure")
    public ResponseEntity configure(@RequestParam("tenantId") String tenantId,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("siteId") String siteId,
                                    @RequestParam("siteName") String siteName,
                                    @RequestParam("appId") String appId,
                                    @RequestParam("oauth_consumer_key") String oauth_consumer_key
    ) {
        parameters.add(tenantId);
        parameters.add(userId);
        parameters.add(userName);
        parameters.add(siteId);
        parameters.add(siteName);
        parameters.add(appId);
        parameters.add(oauth_consumer_key);
        return ResponseEntity.ok(tenantId);
    }

    @GetMapping("/status")
    public ResponseEntity status() {
        return ResponseEntity.ok(parameters.toString());
    }
}

