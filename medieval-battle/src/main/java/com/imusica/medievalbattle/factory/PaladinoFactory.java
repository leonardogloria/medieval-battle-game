package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.MortoVivo;
import com.imusica.medievalbattle.model.Paladino;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PaladinoFactory extends CharacterFactory {
    @Autowired
    CharacterRepository repository;
    @Override
    public Character create() {
        Character paladino = repository.findById("paladino").get();
        return paladino;
    }
}
