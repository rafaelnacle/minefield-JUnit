package com.rafaelnacle.minefield.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int rows;
    private int columns;
    private int mines;
    private final List<Field> fields = new ArrayList<>();

    public Board(int rows, int columns, int mines) {
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        
        generateFields();
        associateNeighbours();
        sortMines();
    }

    private void generateFields() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                fields.add(new Field(i, j));
            }
        }
    }

    private void associateNeighbours() {
        for (Field f1: fields) {
            for (Field f2: fields) {
                f1.addNeighbour(f2);
            }
        }
    }

    private void sortMines() {

    }
}
