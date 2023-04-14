package org.esgi.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

    @Test
    public void testNote() {
        Note note = Note.de(10);

        assertEquals(10, note.getValeur(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoteNegative() {
        Note.de(-1);
    }
}