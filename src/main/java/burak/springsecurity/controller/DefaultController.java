package burak.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/user")
    public String userPage(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("username",httpServletRequest.getRemoteUser());
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    @GetMapping("/manager")
    public String managerPage() {
        return "manager";
    }

}
