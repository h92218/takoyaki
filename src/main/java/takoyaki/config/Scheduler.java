package takoyaki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import takoyaki.controller.MainController;
import takoyaki.service.DataService;

@Component
public class Scheduler {

    @Autowired
    MainController main;

    @Scheduled(fixedRateString = "60000", initialDelay = 60000)
    public void decTime() {
        System.out.println("1분 감소");
        main.setTime(-1);
        System.out.println(main.getTime());
    }
}
