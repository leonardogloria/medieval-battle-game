package com.imusica.medievalbattle.factory;

import com.imusica.medievalbattle.model.Barbaro;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Guerreiro;
import com.imusica.medievalbattle.repository.CharacterRepository;
import com.imusica.medievalbattle.repository.PlayerRepository;
import com.imusica.medievalbattle.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbaroFactory extends CharacterFactory {
    @Autowired
    CharacterService characterService;
    @Override
    public Character create() {
        return null;
    }
}
