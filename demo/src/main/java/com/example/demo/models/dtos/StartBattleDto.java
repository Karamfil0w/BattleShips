package com.example.demo.models.dtos;

import javax.validation.constraints.Positive;

public class StartBattleDto {
    @Positive
    private long attackerId;
    @Positive
    private long defenderId;

    public StartBattleDto() {
    }

    public long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(long attackerId) {
        this.attackerId = attackerId;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public void setDefenderId(long defenderId) {
        this.defenderId = defenderId;
    }
}
