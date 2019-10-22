package com.imusica.medievalbattle.service;

import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    @Autowired
    CharacterRepository repository;
    public final String PALADINO = "paladino";
    public final String GUERREIRO = "guerreiro";
    public final String BARBARO = "barbaro";
    public final String ORC = "orc";
    public final String MORTO_VIVO = "morto-vivo";
    public final String KOBOLD = "kobold";


    public Character createBarbaro(){
        return create(BARBARO);
    }
    public Character createPaladino(){
        return create(PALADINO);
    }
    public Character createGuerreiro(){
        return create(GUERREIRO);
    }
    public Character createKobold(){
        return create(KOBOLD);
    }
    public Character createMortoVivo(){
        return create(MORTO_VIVO);
    }
    public Character createOrc(){
        return create(ORC);
    }
    private Character create(String character){
        return repository.findById(character).get();
    }
}
