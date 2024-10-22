package com.rafaelnacle.minefield;

import com.rafaelnacle.minefield.model.Board;
import com.rafaelnacle.minefield.view.BoardConsole;

public class App {
    public static void main(String[] args) {
        Board board = new Board(6, 6,6);
        new BoardConsole(board);
    }
}
