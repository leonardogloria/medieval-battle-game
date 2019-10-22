package com.imusica.medievalbattle.factory;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.model.Guerreiro;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GuerreiroFactory extends CharacterFactory {
    @Autowired
    CharacterRepository repository;
    @Override
    public Character create() {
        Character guerreiro = repository.findById("guerreiro").get();
        return guerreiro;
    }
}
