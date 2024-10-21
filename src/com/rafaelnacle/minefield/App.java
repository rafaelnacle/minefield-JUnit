package com.rafaelnacle.minefield;

import com.rafaelnacle.minefield.model.Board;

public class App {
    public static void main(String[] args) {
        Board board = new Board(6, 6,6);

        board.open(3, 3);
        board.toggleFlagging(4, 4);
        board.toggleFlagging(4, 5);

        System.out.println(board);
    }
}
