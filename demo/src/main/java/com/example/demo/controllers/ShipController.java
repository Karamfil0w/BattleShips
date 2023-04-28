package com.example.demo.controllers;

import com.example.demo.models.dtos.CreateShipDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final AuthService authService;

    public ShipController(ShipService shipService, AuthService authService) {
        this.shipService = shipService;
        this.authService = authService;
    }

    @ModelAttribute("createShipDto")
    public CreateShipDto initShipDto(){
        return new CreateShipDto();
    }

    @GetMapping("/ships/add")
    public String ships(){
        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDto createShipDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors() || !this.shipService.create(createShipDto)){
            redirectAttributes.addFlashAttribute("createShipDto",createShipDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.createShipDto",bindingResult);
            return "redirect:/ships/add";
        }

        return "redirect:/home";
    }
}
