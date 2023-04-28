package com.example.demo.controllers;

import com.example.demo.models.dtos.ShipDto;
import com.example.demo.models.dtos.StartBattleDto;
import com.example.demo.services.AuthService;
import com.example.demo.services.ShipService;
import com.example.demo.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final LoggedUser loggedUser;
    private final AuthService authService;

    @ModelAttribute("startBattleDto")
    public StartBattleDto initStartBattleDto(){
        return new StartBattleDto();
    }

    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser, AuthService authService) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
        this.authService = authService;
    }

    @GetMapping("/")
    public String loggedOutIndex(){
        if (this.authService.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model){
        long loggedUserId = this.loggedUser.getId();
        if (!this.authService.isLoggedIn()){
            return "redirect:/";
        }
        List<ShipDto> ownShips = this.shipService.getShipsOwnedById(loggedUserId);
        List<ShipDto> enemyShips = this.shipService.getShipsNotOwnedById(loggedUserId);
        List<ShipDto> sortedShips = this.shipService.getAllShipsSorted();

        model.addAttribute("ownShips",ownShips);

        model.addAttribute("enemyShips",enemyShips);

        model.addAttribute("sortedShips",sortedShips);

        return "home";
    }
}
