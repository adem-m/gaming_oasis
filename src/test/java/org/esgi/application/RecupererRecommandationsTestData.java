package org.esgi.application;

import org.esgi.domain.jeu.*;

import java.util.ArrayList;
import java.util.List;

public class RecupererRecommandationsTestData {
    public List<Jeu> JEUX_D_ACTION = new ArrayList<>();
    public List<Jeu> JEUX_D_AVENTURE = new ArrayList<>();
    public List<Jeu> JEUX_RPG = new ArrayList<>();

    public RecupererRecommandationsTestData() {
        for (int i = 0; i < 15; i++) {
            JEUX_D_ACTION.add(new Jeu(
                    new IdJeu("action" + i),
                    "Jeu d'action " + i,
                    "Description",
                    List.of(),
                    new Prix(10.0 + i),
                    List.of(Genre.ACTION),
                    Note.de(9)
            ));

            JEUX_D_AVENTURE.add(new Jeu(
                    new IdJeu("aventure" + i),
                    "Jeu d'aventure " + i,
                    "Description",
                    List.of(),
                    new Prix(10.0 + i),
                    List.of(Genre.AVENTURE),
                    Note.de(9)
            ));

            JEUX_RPG.add(new Jeu(
                    new IdJeu("rpg" + i),
                    "Jeu de rpg " + i,
                    "Description",
                    List.of(),
                    new Prix(10.0 + i),
                    List.of(Genre.RPG),
                    Note.de(9)
            ));
        }
    }
}
