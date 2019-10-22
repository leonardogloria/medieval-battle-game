package com.imusica.medievalbattle.model;

import lombok.Getter;
import lombok.Setter;

public class Action {
    @Getter@Setter
    private int round;
    @Getter@Setter
    private String striker;
    @Getter@Setter
    private String defender;
    @Getter@Setter
    private Boolean isSuccess;
    @Getter@Setter
    private int dano;

    @Override
    public String toString() {
        String action = "Round: " + String.valueOf(round) + " --- ";
        if(isSuccess) {
           action +=  striker +  " atacou " + defender + " com dano de " + String.valueOf(dano);

        } else {
            action += striker + " atacou " + defender + " que conseguiu se defender";

        }

        return action;

    }
}
