package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        int direction = getDirection();

        Position p = new Position(getPosition().row() + direction, getPosition().column());
        if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
            mat[p.row()][p.column()] = true;

            Position p2 = new Position(getPosition().row() + (2 * direction), getPosition().column());
            if (getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p2.row()][p2.column()] = true;
            }
        }

        checkDiagonalCapture(mat, -1);
        checkDiagonalCapture(mat, 1);

        return mat;
    }

    private int getDirection() {
        return (getColor() == Color.WHITE) ? -1 : 1;
    }

    private void checkDiagonalCapture(boolean[][] mat, int columnOffset) {
        Position p = new Position(getPosition().row() + getDirection(), getPosition().column() + columnOffset);
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.row()][p.column()] = true;
        }
    }
}