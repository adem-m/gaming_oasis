package org.esgi.domain;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JeuTest {
    private final Jeu jeu = new Jeu(
            new IdJeu("1"),
            "test",
            "test",
            List.of(),
            new Prix(1),
            List.of(),
            Note.de(1)
    );
    private final Jeu jeu2 = new Jeu(
            new IdJeu("1"),
            "toto",
            "toto",
            List.of(),
            new Prix(10),
            List.of(),
            Note.de(10)
    );
    @Test
    public void testTestEquals() {
        assertEquals(jeu, jeu2);
    }

    @Test
    public void testTestHashCode() {
        assertEquals(jeu.hashCode(), jeu2.hashCode());
    }
}