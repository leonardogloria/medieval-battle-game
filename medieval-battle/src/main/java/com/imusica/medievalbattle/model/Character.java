package com.imusica.medievalbattle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("character")
public class Character {
    @Getter@Setter
    @Id String classe;
    @Getter@Setter
    @JsonIgnore
    int pontosDeVida;
    @JsonIgnore
    @Getter@Setter
    int forca;
    @JsonIgnore
    @Getter@Setter
    int defesa;
    @JsonIgnore
    @Getter@Setter
    int agilidade;
    @JsonIgnore
    @Getter@Setter
    int fatorDeDano;
    @JsonIgnore
    @Getter@Setter
    Integer quantidadeDeDano;

    @JsonValue
    public String toString() {
        return "Personagem";
    }

    public Integer attack(){
        return forca + agilidade;
    }
    public Integer defend(){
        return agilidade + defesa;
    }
    public void removeDano(int dano){
        pontosDeVida -= dano;
    }

}
