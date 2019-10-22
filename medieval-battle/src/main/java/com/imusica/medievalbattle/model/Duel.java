package com.imusica.medievalbattle.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Document(collection = "duels")
public class Duel {
    @Id
    @Getter @Setter
    private String id;
    @Getter@Setter
    private Date date;
    @Indexed
    @Getter@Setter
    private String player;

    @Getter@Setter
    private Character hero;

    @Getter@Setter
    private Character monster;

    @Getter@Setter
    private List<Action> actions;

    @Getter@Setter
    private String winner;
    @Getter@Setter
    private int xp;





}
