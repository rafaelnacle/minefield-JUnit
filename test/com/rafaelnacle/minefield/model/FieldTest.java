package com.rafaelnacle.minefield.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {
    private Field field;

    @BeforeEach
    void initializeField() {
        field = new Field(3, 3);
    }

    @Test
    void testNeighbourDistance1Left() {
        Field neighbour = new Field(3, 2);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNeighbourDistance1Right() {
        Field neighbour = new Field(3, 4);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNeighbourDistance1Upper() {
        Field neighbour = new Field(2, 3);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNeighbourDistance1Lower() {
        Field neighbour = new Field(4, 3);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNeighbourDistance2() {
        Field neighbour = new Field(2, 2);
        boolean result = field.addNeighbour(neighbour);
        assertTrue(result);
    }

    @Test
    void testNotNeighbour() {
        Field neighbour = new Field(1, 1);
        boolean result = field.addNeighbour(neighbour);
        assertFalse(result);
    }
}
