package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.MortoVivo;
import com.imusica.medievalbattle.model.Orc;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MortoVivoFactory extends CharacterFactory {
    @Autowired
    CharacterRepository repository;
    @Override
    public Character create() {
        Character mortoVivo = repository.findById("morto-vivo").get();
        return mortoVivo;
    }
}
