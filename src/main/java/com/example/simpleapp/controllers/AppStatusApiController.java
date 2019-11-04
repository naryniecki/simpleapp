package com.example.simpleapp.controllers;

import com.example.simpleapp.models.Installation;
import com.example.simpleapp.services.InstallationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.example.simpleapp.Constants.ELOQUA_AUTHORIZATION_CODE_ENDPOINT;

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

    @GetMapping("/configure")
    public ModelAndView configure(@RequestParam("installId") String installId,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("siteId") String siteId,
                                  @RequestParam("siteName") String siteName,
                                  @RequestParam("appId") String appId,
                                  @RequestParam("callback") String callback
    ) {
        installationsService.configureApplication(installId, true, true);
        return new ModelAndView("redirect:" + callback);
    }

    @GetMapping("/status")
    public ResponseEntity status(@RequestParam("installId") String installId) {
        return ResponseEntity.ok(installationsService.getInstallationStatus(installId));
    }

    @GetMapping("/all")
    public List<Installation> all() {
        return installationsService.getAllInstallations();
    }

    @PostMapping("/set")
    public ResponseEntity configure(@RequestParam("ai") String ai,
                                    @RequestParam("as") String as,
                                    @RequestParam("di") String di,
                                    @RequestParam("ds") String ds) {

        secrets.add(ai);
        secrets.add(as);
        secrets.add(di);
        secrets.add(ds);
        return ResponseEntity.ok("");
    }

    @GetMapping(ELOQUA_AUTHORIZATION_CODE_ENDPOINT)
    public ResponseEntity code(@RequestParam("code") String code) {
        System.out.println(code);
        return ResponseEntity.ok(code);
    }
}

