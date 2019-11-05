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

import java.util.List;

import static com.example.simpleapp.Constants.ELOQUA_AUTHORIZATION_CODE_ENDPOINT;

@RestController
public class AppStatusApiController {

    @Autowired
    private InstallationsService installationsService;
    private String appId;
    private String installId;
    private String callback;
    private String eloquaCode = null;
    private String dataFoxCode = null;

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
        this.appId = appId;
        this.installId = installId;
        this.callback = callback;
        return new ModelAndView("redirect:https://login.eloqua.com/auth/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=" + appId +
                "&redirect_uri=" + "https://mfhw.herokuapp.com/code" +
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


    @GetMapping(ELOQUA_AUTHORIZATION_CODE_ENDPOINT)
    public ModelAndView code(@RequestParam("code") String code) {
//        if (installationsService.getInstallationStatus(installId).equals("installed") && code.length() > 10) {
        if (eloquaCode == null) { // First auth code is from eloqua always.
            installationsService.configureApplication(installId, true, false);
            return new ModelAndView("redirect:https://app.datafox.com/oauth2/authorize" +
                    "?response_type=code" +
                    "&client_id=" + "37IUhSWc8F1AF5AWq4SO5KsdWwUzKO1c" +
                    "&redirect_uri=" + "https://mfhw.herokuapp.com/code" +
                    "&scope=full");
        }
        installationsService.configureApplication(installId, true, true);
        return new ModelAndView("redirect:" + callback);
    }
}

