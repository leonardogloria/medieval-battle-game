package com.imusica.medievalbattle.controller;

import com.imusica.medievalbattle.model.Duel;
import com.imusica.medievalbattle.repository.DuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DuelController {
    @Autowired
    DuelRepository repository;

    @GetMapping("/duel/{id}")
    public ResponseEntity getDuel(@PathVariable String id){
        Duel duel = repository.findById(id).get();
        if(duel != null){
            return ResponseEntity.ok().body(duel);
        }else {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/duel/player/{player}")
    public ResponseEntity getDuelByPlayer(@PathVariable String player){
        List<Duel> duels = repository.findAllByPlayer(player);
        if(duels != null){
            return ResponseEntity.ok().body(duels);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
