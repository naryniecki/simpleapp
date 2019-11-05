package com.example.simpleapp.services;

import com.example.simpleapp.models.Installation;
import com.example.simpleapp.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void configureApplication(String installId, boolean eloquaTokens, boolean dataFoxTokens) {
        Installation installation = repository.findById(installId).orElseThrow(() -> new RuntimeException("No installation found with id: " + installId));
        installation.setDataFoxTokens(dataFoxTokens);
        installation.setEloquaTokens(eloquaTokens);
        installation.setStatus("configured");
        repository.save(installation);
    }

    public String getInstallationStatus(String installId) {
        Installation installation = repository.findById(installId).orElseThrow(() -> new RuntimeException("No installation found with id: " + installId));
        return installation.getStatus();
    }

    public List<Installation> getAllInstallations() {
        List<Installation> all = new ArrayList<>();
        repository.findAll().forEach(all::add);
        return all;
    }
}
