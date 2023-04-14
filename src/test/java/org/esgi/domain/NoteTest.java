package org.esgi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    @Test
    public void uneNoteDe10DoitAvoirLaValeur10() {
        Note note = Note.de(10);

        assertEquals(10, note.getValeur(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void uneNoteNegativeDoitLeverUneException() {
        Note.de(-1);
    }
}