package com.example.demo.controllers;

import com.example.demo.models.dtos.LoginUserDto;
import com.example.demo.models.dtos.UserRegistrationDto;
import com.example.demo.services.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("loginUserDto")
    public LoginUserDto initLoginUserDto() {
        return new LoginUserDto();
    }


    @ModelAttribute("registrationDto")
    public UserRegistrationDto initRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/register")
    public String register() {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegistrationDto registrationDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors() || !this.authService.register(registrationDto)) {

            redirectAttributes.addFlashAttribute("registrationDto", registrationDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registrationDto", bindingResult);
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginUserDto loginUserDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (this.authService.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginUserDto", loginUserDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginUserDto", loginUserDto);
            return "redirect:/login";
        }
        if (!authService.login(loginUserDto)) {
            redirectAttributes.addFlashAttribute("loginUserDto", loginUserDto);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/login";

        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        this.authService.logout();
        return "redirect:/";
    }
}
