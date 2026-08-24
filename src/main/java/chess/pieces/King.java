package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private static final int[][] OFFSETS = {
            {-1,  0}, // (up)
            { 1,  0}, // (down)
            { 0, -1}, // (left)
            { 0,  1}, // (right)
            {-1, -1}, // (upper left)
            {-1,  1}, // (upper right)
            { 1, -1}, // (bottom left)
            { 1,  1}  // (bottom right)
    };

    public King(Board board, Color color){super(board,color);}

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position){
        return getBoard().positionExists(position)
                && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position));
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        for (int[] offset : OFFSETS) {
            Position targetPosition = new Position(
                    getPosition().row() + offset[0],
                    getPosition().column() + offset[1]
            );

            if (canMove(targetPosition)) {
                mat[targetPosition.row()][targetPosition.column()] = true;
            }
        }

        return mat;
    }
}