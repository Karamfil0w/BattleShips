package com.example.demo.services;

import com.example.demo.models.dtos.StartBattleDto;
import com.example.demo.models.entities.Ship;
import com.example.demo.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private final ShipRepository shipRepository;

    @Autowired
    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDto attackDate){
        Optional<Ship> attackerOpt = this.shipRepository.findById(attackDate.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById(attackDate.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        long newDefenderHealth = defender.getHealth() - attacker.getPower();
        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            this.shipRepository.save(defender);
        }
    }
}
