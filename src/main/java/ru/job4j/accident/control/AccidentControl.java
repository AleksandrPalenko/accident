package ru.job4j.accident.control;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccidentControl {
    private final AccidentService accidents;

    public AccidentControl(AccidentService accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("rules", accidents.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidents.create(accident);
        String[] ids = req.getParameterValues("rIds");
        for (String rules:ids) {
            accident.addRule(accidents.findByRuleId(Integer.parseInt(rules)));
        }
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int accidentId, @NotNull Model model) {
        model.addAttribute("accident", accidents.findById(accidentId));
        return "accident/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidents.update(accident);
        String[] ids = req.getParameterValues("rIds");
        for (String rules:ids) {
            accident.addRule(accidents.findByRuleId(Integer.parseInt(rules)));
        }
        return "redirect:/";
    }

}