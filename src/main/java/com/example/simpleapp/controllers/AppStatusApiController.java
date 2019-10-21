package com.example.simpleapp.controllers;

import com.example.simpleapp.models.Installation;
import com.example.simpleapp.services.InstallationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppStatusApiController {

    @Autowired
    private InstallationsService installationsService;
    private List<String> secrets = new ArrayList<>();

    @PostMapping("/install")
    public ResponseEntity install(@RequestParam("installId") String installId,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("siteId") String siteId,
                                  @RequestParam("siteName") String siteName,
                                  @RequestParam("appId") String appId
    ) {
        installationsService.installApplication(installId, userId, userName, siteId, siteName, appId);
        return ResponseEntity.ok(installId);
    }

    @PostMapping("/uninstall")
    public ResponseEntity uninstall(@RequestParam("installId") String installId,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("siteId") String siteId,
                                    @RequestParam("siteName") String siteName
    ) {
        installationsService.uninstallApplication(installId);
        return ResponseEntity.ok(installId);
    }

    @PostMapping("/configure")
    public ResponseEntity configure(@RequestParam("installId") String installId,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("siteId") String siteId,
                                    @RequestParam("siteName") String siteName,
                                    @RequestParam("appId") String appId
    ) {
        installationsService.configureApplication(installId,true, true);
        return ResponseEntity.ok(installId);
    }

    @GetMapping("/status")
    public ResponseEntity status(@RequestParam("installId") String installId) {
        return ResponseEntity.ok(installationsService.getInstallationStatus(installId));
    }

    @GetMapping("/all")
    public List<Installation> all() {
        return installationsService.getAllInstallations();
    }
}

