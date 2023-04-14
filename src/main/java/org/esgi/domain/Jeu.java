package org.esgi.domain;

import java.util.List;
import java.util.Objects;

public final class Jeu {
    private final IdJeu id;
    private final String titre;
    private final String description;
    private final List<Avis> avis;
    private final Prix prix;
    private final List<Genre> genres;
    private final Note note;

    public Jeu(
            IdJeu id,
            String titre,
            String description,
            List<Avis> avis,
            Prix prix,
            List<Genre> genres,
            Note note
    ) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.avis = avis;
        this.prix = prix;
        this.genres = genres;
        this.note = note;
    }

    public IdJeu id() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Jeu) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
