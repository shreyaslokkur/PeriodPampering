package com.lks;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties("com.lks")
public class MRVManagementProperties {

    @NotNull
    private String emailUsername;

    @NotNull
    private String emailPassword;

    @NotNull
    private String adminEmail;

    @NotNull
    private String smsUsername;

    @NotNull
    private String smsHash;

    @NotNull
    private String smsSender;

    @NotNull
    private String smsMessage;

    @NotNull
    private String smsRepeatMessage;

    @NotNull
    private String facebookUrl;

    @NotNull
    private String googleUrl;

    @NotNull
    private String emailPort;

    @NotNull
    private String emailHost;

    @NotNull
    private String errorEmailUsername;

    @NotNull
    private String errorEmailPassword;

    @NotNull
    private String tallyURL;

    @NotNull
    private boolean isUAT;

    @NotNull
    private String uatEmail;

    @NotNull
    private String uatSmsNumber;

    @NotNull
    private String uatSmsMessage;


    public String getEmailUsername() {
        return emailUsername;
    }

    public void setEmailUsername(String emailUsername) {
        this.emailUsername = emailUsername;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getSmsUsername() {
        return smsUsername;
    }

    public void setSmsUsername(String smsUsername) {
        this.smsUsername = smsUsername;
    }

    public String getSmsHash() {
        return smsHash;
    }

    public void setSmsHash(String smsHash) {
        this.smsHash = smsHash;
    }

    public String getSmsSender() {
        return smsSender;
    }

    public void setSmsSender(String smsSender) {
        this.smsSender = smsSender;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getSmsRepeatMessage() {
        return smsRepeatMessage;
    }

    public void setSmsRepeatMessage(String smsRepeatMessage) {
        this.smsRepeatMessage = smsRepeatMessage;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getGoogleUrl() {
        return googleUrl;
    }

    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }

    public String getErrorEmailUsername() {
        return errorEmailUsername;
    }

    public void setErrorEmailUsername(String errorEmailUsername) {
        this.errorEmailUsername = errorEmailUsername;
    }

    public String getErrorEmailPassword() {
        return errorEmailPassword;
    }

    public void setErrorEmailPassword(String errorEmailPassword) {
        this.errorEmailPassword = errorEmailPassword;
    }

    public String getEmailPort() {
        return emailPort;
    }

    public void setEmailPort(String emailPort) {
        this.emailPort = emailPort;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getTallyURL() {
        return tallyURL;
    }

    public void setTallyURL(String tallyURL) {
        this.tallyURL = tallyURL;
    }

    public boolean isUAT() {
        return isUAT;
    }

    public void setUAT(boolean isUAT) {
        this.isUAT = isUAT;
    }

    public String getUatEmail() {
        return uatEmail;
    }

    public void setUatEmail(String uatEmail) {
        this.uatEmail = uatEmail;
    }

    public String getUatSmsNumber() {
        return uatSmsNumber;
    }

    public void setUatSmsNumber(String uatSmsNumber) {
        this.uatSmsNumber = uatSmsNumber;
    }

    public String getUatSmsMessage() {
        return uatSmsMessage;
    }

    public void setUatSmsMessage(String uatSmsMessage) {
        this.uatSmsMessage = uatSmsMessage;
    }
}
