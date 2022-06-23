package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        List<String> accounts = List.of("Account1", "Account2", "Account3");
        model.addAttribute("accounts", accounts);
        return "index";
    }
}
