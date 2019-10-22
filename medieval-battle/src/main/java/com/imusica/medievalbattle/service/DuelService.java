package com.imusica.medievalbattle.service;

import com.imusica.medievalbattle.exception.HeroNotFoundException;
import com.imusica.medievalbattle.model.*;
import com.imusica.medievalbattle.model.Character;
import com.imusica.medievalbattle.repository.DuelRepository;
import com.imusica.medievalbattle.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DuelService {
    Logger logger = LoggerFactory.getLogger(DuelService.class);
    public static final int KOBOLD = 3;
    public static final int ORC = 2;
    public static final int MORTO_VIVO = 1;

    @Autowired
    DiceService diceService;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    CharacterService characterService;
    @Autowired
    DuelRepository duelRepository;

    public int calculateInitiative(Character hero, Character monster){
        Integer monsterIniciative = monster.getAgilidade() + diceService.rollD10();
        Integer heroIniciative = hero.getAgilidade() + diceService.rollD10();
        while(monsterIniciative == heroIniciative){
            monsterIniciative = monster.getAgilidade() + diceService.rollD10();
            heroIniciative = hero.getAgilidade() + diceService.rollD10();
        }
        if(heroIniciative > monsterIniciative){
            return 1;
        } else{
            return -1;
        }
    }
    public DuelVO duel(Player player, Character hero, Character monster){

        logger.info("Iniciando Duelo");
        logger.info("Calculando iniciativa");
        DuelVO duelVO = new DuelVO();
        UUID uuid = UUID.randomUUID();
        ArrayList<Action> duelActions = new ArrayList<Action>();
        duelVO.setUuid(uuid);
        Integer iniciative = calculateInitiative(hero,monster);
        List<Character> battle;
        if(iniciative == 1){
            logger.info("Hero Win");
            battle = Arrays.asList(hero,monster);
        }else {
            logger.info("Monster Win");
            battle = Arrays.asList(monster,hero);
        }
        Duel duel = new Duel();
        duel.setPlayer(player.getName());
        duel.setHero(hero);
        duel.setMonster(monster);
        duel.setId(uuid.toString());
        duel.setDate(new Date());

        int round = 0;
        while (continueDuel(hero,monster)){
            Action action = new Action();

            logger.info("Round " + (round + 1));
            Character striker;
            Character defender;
            if(round % 2 == 0){
                striker = battle.get(0);
                defender = battle.get(1);
            }else {
                striker = battle.get(1);
                defender = battle.get(0);

            }
            action.setStriker(striker.getClasse());
            action.setDefender(defender.getClasse());
            action.setRound(round + 1);

            if(attack(striker,defender)){
                logger.info(striker + " conseguiu atacar");
                int dano = calculateDano(striker);
                logger.info("Dano: " + dano);
                action.setIsSuccess(true);
                action.setDano(dano);
                defender.removeDano(dano);
            }else {
                action.setIsSuccess(false);
                logger.info(striker + " não conseguiu o ataque");
            }
            round++;
            duelActions.add(action);

        }
        duel.setActions(duelActions);
        logger.info("Pontos de vida do heroi: " + hero.getPontosDeVida() );
        logger.info("Pontos de vida do monstro: " + monster.getPontosDeVida() );
        if(heroWon(hero,monster)){
            int xp = calculateXP(round);
            duelVO.setRounds(round + 1);
            duelVO.setWinner(hero);
            duelVO.setXp(xp);
            duelVO.setStatus("Won");
            duel.setWinner(hero.getClasse());
            duel.setXp(duelVO.getXp());
            playerRepository.incrementXP(player,duelVO.getXp());

            logger.info(hero + " ganhou! E ganhou " + xp + " de XP ");
        }else {
            duel.setWinner(monster.getClasse());
            duelVO.setWinner(monster);
            duelVO.setRounds(round);
            duelVO.setStatus("Lost");
            logger.info(monster +  " ganhou");
        }
        duelRepository.save(duel);
        return duelVO;


    }
    public Character getMonster() {
        Character monster = null;
        Random r = new Random();
        int character = r.nextInt((3 - 1) + 1) + 1;
        switch (character){
            case 1: monster =  characterService.createMortoVivo();
                break;
            case 2: monster =  characterService.createOrc();
                break;
            case 3: monster =  characterService.createKobold();
                break;
        }
        return monster;
    }
    public Character getHero(String heroPayload) throws HeroNotFoundException {
        heroPayload = heroPayload.toUpperCase();
        Character hero = null;
        switch (heroPayload){
            case "GUERREIRO": hero = characterService.createGuerreiro();
                break;
            case "BARBARO": hero = characterService.createBarbaro();
                break;
            case "PALADINO": hero = characterService.createPaladino();
                break;
            default: throw new HeroNotFoundException("Herói não localizado");

        }
        return hero;
    }
    private int calculateDano(Character striker){
        int dano = 0;
        logger.info("Lançando " + striker.getQuantidadeDeDano() + "d" + striker.getFatorDeDano());
        for(int i = 0; i< striker.getQuantidadeDeDano(); i++ ){
            dano += diceService.rollDice(striker.getFatorDeDano());
        }
        return dano;
    }

    public Boolean attack(Character striker, Character defender){
        Integer attackFactor = calculateAtack(striker);
        Integer defenseFactor = calculateDefense(defender);
        if(attackFactor > defenseFactor) {
            return true;
        }else {
            return false;
        }
    }
    private Boolean continueDuel(Character hero, Character monster){
        if(hero.getPontosDeVida() <= 0 || monster.getPontosDeVida() <= 0){
            return false;
        }else {
            return true;
        }
    }
    private boolean heroWon(Character hero, Character monster){
        if(hero.getPontosDeVida() > monster.getPontosDeVida()){
            return true;
        }
        else {
           return false;
        }
    }
    private int calculateXP(int turns){
        return 100 - turns;
    }
    private Integer calculateAtack(Character striker){
        return diceService.rollD10() + striker.getAgilidade() + striker.getForca();
    }
    private Integer calculateDefense(Character defender){
        return diceService.rollD10() + defender.getAgilidade() + defender.getDefesa();
    }


}

