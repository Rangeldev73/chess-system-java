package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

    private static final int[][] DIRECTIONS = {
            {-1, -1}, // upper left
            {-1,  1}, // upper right
            { 1, -1}, // bottom left
            { 1,  1}  // bottom right
    };

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        return slidingMoves(DIRECTIONS);
    }
}