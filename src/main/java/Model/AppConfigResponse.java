package Model;

import java.util.ArrayList;

public class AppConfigResponse {
    private String platform;
    private String latestVersion;
    private String appUrl;
    private String splashScreen;
    private String authScreen;
    private ArrayList countries;

    public AppConfigResponse() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getSplashScreen() {
        return splashScreen;
    }

    public void setSplashScreen(String splashScreen) {
        this.splashScreen = splashScreen;
    }

    public String getAuthScreen() {
        return authScreen;
    }

    public void setAuthScreen(String authScreen) {
        this.authScreen = authScreen;
    }

    public ArrayList getCountries() {
        return countries;
    }

    public void setCountries(ArrayList countries) {
        this.countries = countries;
    }
}
