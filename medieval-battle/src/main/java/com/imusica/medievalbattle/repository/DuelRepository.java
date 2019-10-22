package com.imusica.medievalbattle.repository;

import com.imusica.medievalbattle.model.Duel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DuelRepository extends MongoRepository<Duel,String> {

    @Query("{player:'?0'}")
    List<Duel> findAllByPlayer(String player);
}
