package com.example.simpleapp.services;

import com.example.simpleapp.models.Installation;
import com.example.simpleapp.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallationsService {

    @Autowired
    LocalRepository repository;

    public void installApplication(String installId, String userId, String userName, String siteId, String siteName, String appId) {
        Installation installation = new Installation();
        installation.setInstallId(installId);
        installation.setUserId(userId);
        installation.setUserName(userName);
        installation.setSiteId(siteId);
        installation.setSiteName(siteName);
        installation.setAppId(appId);
        installation.setStatus("installed");
        repository.save(installation);
    }

    public void uninstallApplication(String installId) {
        repository.deleteById(installId);
    }

    public void configureApplication(String installId, String eloquaTokens, String dataFoxTokens) {
        Installation installation = repository.findById(installId).orElseThrow(() -> new RuntimeException("No installation found with id: " + installId));
        installation.setDataFoxTokens(eloquaTokens);
        installation.setEloquaTokens(dataFoxTokens);
        installation.setStatus("configured");
    }

    public Installation getInstallationStatus(String installId) {
        Installation installation = repository.findById(installId).orElseThrow(() -> new RuntimeException("No installation found with id: " + installId));
        return installation;
    }
}
