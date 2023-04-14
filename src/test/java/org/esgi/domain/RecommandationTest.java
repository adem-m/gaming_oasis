package org.esgi.domain;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RecommandationTest {
    @Test
    public void laRecommandationNeDoitPasAvoirPlusDe10JeuxParGenre() {
        Recommandation recommandation = Recommandation.vide();
        List<Jeu> jeux = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            jeux.add(new Jeu(
                    "1",
                    "Jeu 1",
                    "Descri",
                    List.of(),
                    new Prix(10.0),
                    List.of(),
                    Note.de(9)
            ));
        }
        recommandation.ajouter(jeux);

        assertEquals(10, recommandation.jeux().size());
    }
}