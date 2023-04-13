/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.googleLogin;

/**
 *
 * @author gaurav.khanzode
 */
public class GoogleAuthenticationBean {

    private String profileId;
    private String profileEmail;
    private String authenticationStatus;
    private String loginId;
    private String loginPassword;

    public GoogleAuthenticationBean() {
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Override
    public String toString() {
        return "{\"profileId\":\"" + profileId + "\", \"profileEmail\":\"" + profileEmail + "\", \"authenticationStatus\":\"" + authenticationStatus + "\", \"loginId\":\"" + loginId + "\", \"loginPassword\":\"" + loginPassword + "\"}";
    }

}
