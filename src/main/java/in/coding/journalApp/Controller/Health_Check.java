package in.coding.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health_Check {

    @GetMapping("health-check")
    public String ok() {
        return "Health Check ok --- ";
    }
}
