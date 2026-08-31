package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    private static final int[][] DIRECTIONS = {
            {-1,  0}, // up
            { 1,  0}, // down
            { 0, -1}, // left
            { 0,  1},  // right
            {-1, -1}, // upper left
            {-1,  1}, // upper right
            { 1, -1}, // bottom left
            { 1,  1}  // bottom right
    };

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        return slidingMoves(DIRECTIONS);
    }
}