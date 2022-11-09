package Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class AppConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String platform;
    private String minVersion;
    private String latestVersion;
    private String appUrl;
    private String splashScreen;
    private String authScreen;



}
