package com.example.simpleapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Installation {

    @Id
    private String installId;
    private String userId;
    private String userName;
    private String siteId;
    private String siteName;
    private String appId;
    private String status; // installed, configured, data-matched, data-enriched
    private String eloquaTokens; // present, absent, incomplete
    private String dataFoxTokens; //present, absent, incomplete

    public Installation() {
    }

    public Installation(String installId, String userId, String userName, String siteId, String siteName, String appId, String status, String eloquaTokens, String dataFoxTokens) {
        this.installId = installId;
        this.userId = userId;
        this.userName = userName;
        this.siteId = siteId;
        this.siteName = siteName;
        this.appId = appId;
        this.status = status;
        this.eloquaTokens = eloquaTokens;
        this.dataFoxTokens = dataFoxTokens;
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEloquaTokens() {
        return eloquaTokens;
    }

    public void setEloquaTokens(String eloquaTokens) {
        this.eloquaTokens = eloquaTokens;
    }

    public String getDataFoxTokens() {
        return dataFoxTokens;
    }

    public void setDataFoxTokens(String dataFoxTokens) {
        this.dataFoxTokens = dataFoxTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Installation that = (Installation) o;
        return installId.equals(that.installId) &&
                userId.equals(that.userId) &&
                Objects.equals(userName, that.userName) &&
                siteId.equals(that.siteId) &&
                Objects.equals(siteName, that.siteName) &&
                appId.equals(that.appId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(eloquaTokens, that.eloquaTokens) &&
                Objects.equals(dataFoxTokens, that.dataFoxTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installId, userId, userName, siteId, siteName, appId, status, eloquaTokens, dataFoxTokens);
    }

    @Override
    public String toString() {
        return "Installation{" +
                "installId='" + installId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", siteId='" + siteId + '\'' +
                ", siteName='" + siteName + '\'' +
                ", appId='" + appId + '\'' +
                ", status='" + status + '\'' +
                ", eloquaTokens='" + eloquaTokens + '\'' +
                ", dataFoxTokens='" + dataFoxTokens + '\'' +
                '}';
    }
}
