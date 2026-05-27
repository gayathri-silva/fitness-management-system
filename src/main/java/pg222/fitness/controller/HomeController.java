package pg222.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/contact-us")
    public String contactUsPage() {
        return "contact-us"; // Render the contact-us.html template
    }
}