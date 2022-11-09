package Controller;

import Model.AppConfigResponse;
import Model.DefaultResponse;
import Service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/app")
public class AppConfigController {

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final AppConfigService appConfigService;

    @Autowired
    public AppConfigController(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    @PostMapping("/config/check/update")
    public DefaultResponse checkAppUpdates(@RequestBody HashMap<String, Object> payload) {
        AppConfigResponse appConfigResponse = appConfigService.checkForDeviceUpdates(payload);
        Map<String, Object> data = new HashMap<>();
        data.put("app_config_data", appConfigResponse);
        if(appConfigResponse!=null){
            return new DefaultResponse(200, "Success", "App is up to date", data);
        }else{
            return new DefaultResponse(400, "fail", "Invalid Platform", data);
        }
    }
}
