package org.esgi.domain.jeu;

import org.esgi.domain.Utilisateur;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AvisTest {

    @Test
    public void testAvis() {
        Utilisateur utilisateur = new Utilisateur("id", List.of());
        Avis avis = new Avis("contenu", utilisateur);
        assertEquals("contenu", avis.contenu());
        assertEquals(utilisateur, avis.utilisateur());
    }
}
