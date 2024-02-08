package az.asifkazim.bol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String listProducts() {
        return "redirect:/print";
    }

}
