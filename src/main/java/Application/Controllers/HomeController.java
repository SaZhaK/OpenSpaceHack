package Application.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "home";
    }
}
