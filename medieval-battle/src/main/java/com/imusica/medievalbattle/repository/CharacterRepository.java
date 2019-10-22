package com.imusica.medievalbattle.repository;

import com.imusica.medievalbattle.model.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CharacterRepository extends CrudRepository<Character,String> {
}
