package com.example.demo.controllers;

import com.example.demo.models.dtos.StartBattleDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.BattleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BattleController {

    private final BattleService battleService;
    private final AuthService authService;

    public BattleController(BattleService battleService, AuthService authService) {
        this.battleService = battleService;
        this.authService = authService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDto startBattleDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("startBattleDto",startBattleDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.startBattleDto",bindingResult);
            return "redirect:/home";
        }

        this.battleService.attack(startBattleDto);

        return "redirect:/home";
    }
}
