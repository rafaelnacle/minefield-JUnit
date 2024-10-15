package com.rafaelnacle.minefield.model;

import com.rafaelnacle.minefield.exception.ExplosionException;
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

    @Test
    void testDefaultValueFlaggedAttribute() {
        assertFalse(field.isFlagged());
    }

    @Test
    void testToggleFlagging() {
        field.toggleFlagging();
        assertTrue(field.isFlagged());
    }

    @Test
    void testToggleFlaggingTwoTimes() {
        field.toggleFlagging();
        field.toggleFlagging();
        assertFalse(field.isFlagged());
    }

    @Test
    void testOpenNotMinedNotFlagged() {
        assertTrue(field.open());
    }

    @Test
    void testOpenNotMinedFlagged() {
        field.toggleFlagging();
        assertFalse(field.open());
    }

    @Test
    void testOpenMinedFlagged() {
        field.toggleFlagging();
        field.mine();
        assertFalse(field.open());
    }

    @Test
    void testOpenMinedNotFlagged() {
        field.mine();

        assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

    @Test
    void testOpenNeighbourhood1() {
        Field field11 = new Field(1, 1);
        Field field22 = new Field(2,2);

        field22.addNeighbour(field11);

        field.addNeighbour(field22);
        field.open();

        assertTrue(field22.isOpen() && field11.isOpen());
    }

    @Test
    void testOpenNeighbourhood2() {
        Field field11 = new Field(1, 1);
        Field field12 = new Field(1, 1);
        field12.mine();

        Field field22 = new Field(2,2);
        field22.addNeighbour(field11);
        field22.addNeighbour(field12);

        field.addNeighbour(field22);
        field.open();

        assertTrue(field22.isOpen() && field11.isClosed());
    }
}
