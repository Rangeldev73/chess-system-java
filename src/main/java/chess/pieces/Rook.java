package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    private static final int[][] DIRECTIONS = {
            {-1,  0}, // up
            { 1,  0}, // down
            { 0, -1}, // left
            { 0,  1}  // right
    };

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        return slidingMoves(DIRECTIONS);
    }
}