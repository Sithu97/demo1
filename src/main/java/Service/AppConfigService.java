package Service;

import Entity.AppConfig;
import Entity.Country;
import Model.AppConfigResponse;
import Repository.AppConfigRepository;
import Repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppConfigService {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final AppConfigRepository appConfigRepository;

    private final CountryRepository countryRepository;

    public AppConfigService(AppConfigRepository appConfigRepository, CountryRepository countryRepository) {
        this.appConfigRepository = appConfigRepository;
        this.countryRepository = countryRepository;
    }
    public AppConfigResponse checkForDeviceUpdates(HashMap<String, Object> payload) {

        String platform = ((HashMap<String, Object>)payload.get("device")).get("platform").toString();
        AppConfig appConfig = appConfigRepository.findAppConfigByPlatformIgnoreCase(platform);
        if (appConfig != null) {
            double deviceVersion = Double.parseDouble(((HashMap<String, Object>)payload.get("device")).get("app_version").toString());
            double minAppVersion = Double.parseDouble(appConfig.getMinVersion());
            double latestAppVersion = Double.parseDouble(appConfig.getLatestVersion());
            AppConfigResponse data;
            if (deviceVersion >= minAppVersion) {
                if (deviceVersion < latestAppVersion) {
                    LOGGER.log(Level.INFO, "There is an update to install...app-version : " + deviceVersion + " latest version : " + latestAppVersion);
                    data = creteAppConfigResponse(appConfig, false, true, "Update your app to latest version");
                    return  data;
                } else {
                    LOGGER.log(Level.INFO, "App version is up to date.");
                    data = creteAppConfigResponse(appConfig, false, false, "App is up to date");
                    return data;
                }
            } else {
                LOGGER.log(Level.SEVERE, "User app version is lower than the minimum version");
                data = creteAppConfigResponse(appConfig, true, true, "Update need to proceed further");
                return data;
            }
        } else {
            LOGGER.log(Level.SEVERE, "Invalid Platform...");
            return null;
        }
    }
    public AppConfigResponse creteAppConfigResponse(AppConfig appConfig, boolean forceUpdate, boolean updateAvailable, String message) {

        AppConfigResponse appConfigResponse = new AppConfigResponse();
        appConfigResponse.setPlatform(appConfig.getPlatform());
        appConfigResponse.setLatestVersion(appConfig.getLatestVersion());
        appConfigResponse.setAppUrl(appConfig.getAppUrl());
        appConfigResponse.setAuthScreen(appConfig.getAuthScreen());
        appConfigResponse.setSplashScreen(appConfig.getSplashScreen());

        List<Country> countries = countryRepository.findAllByStateOrderByName(CountryRepository.STATE_ACTIVE);
        ArrayList<HashMap<String, Object>> countryList = new ArrayList<>();
        countries.forEach(country -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("id", country.getId());
                    map.put("name", country.getName());
                    map.put("code", country.getCode());
                    map.put("min_length", country.getMinLength());
                    map.put("max_length", country.getMaxLength());
                    countryList.add(map);
                }
        );
        appConfigResponse.setCountries(countryList);

        return appConfigResponse;

    }

}
