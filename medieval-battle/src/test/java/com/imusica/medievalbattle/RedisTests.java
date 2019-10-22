package com.imusica.medievalbattle;


import com.imusica.medievalbattle.factory.KoboldFactory;
import com.imusica.medievalbattle.model.*;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.repository.CharacterRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataRedisTest
public class RedisTests {
    @Autowired
    CharacterRepository repository;

    @Before
    public void createCharacters(){
        Kobold kobold = new Kobold();
        kobold.setClasse("kobold");
        kobold.setPontosDeVida(12);
        kobold.setForca(4);
        kobold.setDefesa(3);
        kobold.setAgilidade(4);
        kobold.setFatorDeDano(2);
        kobold.setQuantidadeDeDano(3);

        MortoVivo mortoVivo = new MortoVivo();
        mortoVivo.setClasse("morto-vivo");
        mortoVivo.setPontosDeVida(25);
        mortoVivo.setForca(4);
        mortoVivo.setDefesa(0);
        mortoVivo.setAgilidade(1);
        mortoVivo.setFatorDeDano(4);
        mortoVivo.setQuantidadeDeDano(2);

        Orc orc = new Orc();
        orc.setClasse("orc");
        orc.setPontosDeVida(20);
        orc.setForca(6);
        orc.setDefesa(2);
        orc.setAgilidade(2);
        orc.setFatorDeDano(8);
        orc.setQuantidadeDeDano(1);

        Guerreiro guerreiro = new Guerreiro();
        guerreiro.setClasse("guerreiro");
        guerreiro.setAgilidade(3);
        guerreiro.setPontosDeVida(12);
        guerreiro.setForca(4);
        guerreiro.setDefesa(3);
        guerreiro.setQuantidadeDeDano(2);
        guerreiro.setFatorDeDano(4);

        Barbaro barbaro = new Barbaro();
        barbaro.setClasse("barbaro");
        barbaro.setAgilidade(3);
        barbaro.setPontosDeVida(13);
        barbaro.setForca(6);
        barbaro.setDefesa(1);
        barbaro.setQuantidadeDeDano(6);
        barbaro.setFatorDeDano(2);

        Paladino paladino = new Paladino();
        paladino.setClasse("paladino");
        paladino.setPontosDeVida(25);
        paladino.setForca(4);
        paladino.setDefesa(0);
        paladino.setAgilidade(1);
        paladino.setFatorDeDano(4);
        paladino.setQuantidadeDeDano(2);

        repository.save(kobold);
        repository.save(orc);
        repository.save(mortoVivo);
        repository.save(guerreiro);
        repository.save(paladino);
        repository.save(barbaro);
    }



    @Test
    public void testGetKobold(){
        Character kobold = repository.findById("kobold").get();
        Assert.assertNotNull(kobold);
        Assert.assertEquals("kobold",kobold.getClasse());
    }


}
