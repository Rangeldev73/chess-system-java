package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private final Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(getPosition());
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p != null && p.getColor() != color;
    }

    protected boolean canMove(Position position) {
        return getBoard().positionExists(position)
                && (!getBoard().thereIsAPiece(position) || isThereOpponentPiece(position));
    }

    protected boolean[][] slidingMoves(int[][] directions) {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        for (int[] dir : directions) {
            Position p = new Position(getPosition().row() + dir[0], getPosition().column() + dir[1]);

            while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.row()][p.column()] = true;
                p = new Position(p.row() + dir[0], p.column() + dir[1]);
            }

            if (canMove(p)) {
                mat[p.row()][p.column()] = true;
            }
        }

        return mat;
    }
}