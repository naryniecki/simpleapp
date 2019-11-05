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
    public ModelAndView install(@RequestParam("installId") String installId,
                                  @RequestParam("userId") String userId,
                                  @RequestParam("userName") String userName,
                                  @RequestParam("siteId") String siteId,
                                  @RequestParam("siteName") String siteName,
                                  @RequestParam("appId") String appId,
                                  @RequestParam("callback") String callback
    ) {
        installationsService.installApplication(installId, userId, userName, siteId, siteName, appId);
        return new ModelAndView("redirect:https://login.eloqua.com/auth/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=" + appId +
                "&redirect_uri=" + callback +
                "&scope=full");
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
    public ResponseEntity configure(@RequestParam("installId") String installId,
                                    @RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("siteId") String siteId,
                                    @RequestParam("siteName") String siteName,
                                    @RequestParam("appId") String appId
    ) {
        installationsService.configureApplication(installId, true, true);
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
    public ModelAndView code(@RequestParam("code") String code) {
        if (code.length() >10) installationsService.configureApplication("7a9fff95-5b11-4bb7-9946-886d3ff4b4a3", true, true);
        return new ModelAndView("redirect:https://secure.eloqua.com/Apps/Cloud/Admin/Install/Callback/7a9fff95-5b11-4bb7-9946-886d3ff4b4a3&guid=42-58-FC-94-35-64-AC-D5-28-06-18-5E-F3-08-48-2D");
    }
}

