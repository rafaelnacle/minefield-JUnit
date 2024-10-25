package com.rafaelnacle.minefield.view;

import com.rafaelnacle.minefield.exception.ExitException;
import com.rafaelnacle.minefield.exception.ExplosionException;
import com.rafaelnacle.minefield.model.Board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class BoardConsole {
    private Board board;
    private Scanner scan = new Scanner(System.in);

    public BoardConsole(Board board) {
        this.board = board;

        executeGame();
    }

    private void executeGame() {
        try {
            boolean continueGame = true;

            while (continueGame) {
                gameLoop();

                System.out.println("Want to play another match? (Y/n) ");
                String answer = scan.nextLine();

                if("n".equalsIgnoreCase(answer)) {
                    continueGame = false;
                } else {
                    board.reset();
                }
            }

        } catch (ExitException e) {
            System.out.println("Bye!");
        } finally {
            scan.close();
        }
    }

    private void gameLoop() {
        try {
            while(!board.achievedObjective()) {
                System.out.println(board.toString());

                String input = captureInputValue("Enter (Row, Column): ");

                Iterator<Integer> rowColumn = Arrays.stream(input.split(","))
                        .map(e -> Integer.parseInt(e.trim())).iterator();

                input =captureInputValue("1 - Open Field or 2 - Toggle Flag: ");

                if ("1".equalsIgnoreCase(input)) {
                    board.open(rowColumn.next(), rowColumn.next());
                } else if ("2".equalsIgnoreCase(input)) {
                    board.toggleFlagging(rowColumn.next(), rowColumn.next());
                }
            }

            System.out.println("You win!");
        } catch (ExplosionException e) {
            System.out.println(board);
            System.out.println("You lost!");
        }
    }

    private String captureInputValue(String text) {
        System.out.print(text);
        String input = scan.nextLine();

        if ("exit".equalsIgnoreCase(input)) {
            throw new ExitException();
        }

        return input;
    }
}
