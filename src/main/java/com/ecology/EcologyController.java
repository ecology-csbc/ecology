package com.ecology;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Веб-контролер для перегляду та редагування екологічних записів.
 */
@Controller
public class EcologyController {

    private final EcologyRepository ecologyRepository;

    public EcologyController(EcologyRepository ecologyRepository) {
        this.ecologyRepository = ecologyRepository;
    }

    @GetMapping("/")
    public String viewEcology(Model model) {
        List<Ecology> ecologies = ecologyRepository.findAll();
        model.addAttribute("ecologies", ecologies);
        return "ecology";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ecology", new Ecology());
        return "add";
    }

    @PostMapping("/add")
    public String addEcology(@ModelAttribute Ecology ecology) {
        ecologyRepository.save(ecology);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteEcology(@PathVariable String id) {
        ecologyRepository.deleteById(id);
        return "redirect:/";
    }
}
