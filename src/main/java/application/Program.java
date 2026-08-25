package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.println("Turn: " + chessMatch.getTurn());
                System.out.println("Waiting move for: " + chessMatch.getCurrentPlayer());
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                chessMatch.performChessMove(source, target);

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Press Enter to continue...");
                sc.nextLine();
            }
        }
    }
}