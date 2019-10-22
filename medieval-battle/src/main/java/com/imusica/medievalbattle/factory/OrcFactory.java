package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Kobold;
import com.imusica.medievalbattle.model.Orc;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrcFactory extends CharacterFactory {
    @Autowired
    CharacterRepository repository;
    @Override
    public Character create() {
        Character orc = repository.findById("orc").get();
        return orc;
    }
}
