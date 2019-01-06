package cz.zcu.pia.revoloot.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void isCZ() {
        assertTrue(State.CZ.isCZ());
        assertFalse(State.CZ.isSVK());
    }

    @Test
    void isSVK() {
        assertFalse(State.SVK.isCZ());
        assertTrue(State.SVK.isSVK());
    }

    @Test
    void fromStringCZ() {
        String name = "cZ";
        State resolved = State.fromString(name);
        assertEquals(State.CZ, resolved);
    }

    @Test
    void fromStringSVK() {
        String name = "sVk";
        State resolved = State.fromString(name);
        assertEquals(State.SVK, resolved);
    }

    @Test
    void fromStringFail() {
        String name = "boo";
        try {
            State resolved = State.fromString(name);
            fail();
        }catch (IllegalArgumentException ex){
            assertTrue(true);
        }
    }
}