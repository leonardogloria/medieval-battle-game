package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Kobold;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class KoboldFactory  extends CharacterFactory {
    @Autowired
    CharacterRepository repository;

    @Override
    public Character create() {
        Optional<Character> kobold = repository.findById("kobold");
        return kobold.get();

    }
}
