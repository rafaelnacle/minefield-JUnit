package com.rafaelnacle.minefield.model;

import com.rafaelnacle.minefield.exception.ExplosionException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public void open(int row, int column) {
        try {
            fields.parallelStream()
                    .filter(f -> f.getRow() == row && f.getColumn() == column)
                    .findFirst()
                    .ifPresent(f -> f.open());
        } catch (ExplosionException e) {
            fields.forEach(f -> f.setOpen(true));
            throw e;
        }
    }

    public void toggleFlagging(int row, int column) {
        fields.parallelStream()
                .filter(f -> f.getRow() == row && f.getColumn() == column)
                .findFirst()
                .ifPresent(f -> f.toggleFlagging());
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
        long plantedMines = 0;

        Predicate<Field> mined = f -> f.isMined();

        do {
            int random = (int) (Math.random() * fields.size());
            fields.get(random).mine();
            plantedMines = fields.stream().filter(mined).count();
        } while (plantedMines < mines);
    }

    public boolean achievedObjective() {
        return fields.stream().allMatch(f -> f.achievedObjective());
    }

    public void reset() {
        fields.stream().forEach(f -> f.reset());
        sortMines();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("  ");
        for (int c = 0; c < columns; c++) {
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");

        int i = 0;
        for (int r = 0; r < rows; r++) {
            sb.append(r);
            sb.append(" ");
            for (int c = 0; c < columns; c++) {
                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
