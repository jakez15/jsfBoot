package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirect() {
        System.out.println("**** REDIRECTING TO INDEX.XHTML ****");
        return "redirect:/index.xhtml";
    }

}
