package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private final Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
        this.moveCount = 0;
    }

    public Color getColor() {
        return color;
    }

    protected int getMoveCount() {
        return moveCount;
    }

    void increaseMoveCount() {
        moveCount++;
    }

    void decreaseMoveCount() {
        moveCount--;
    }

    Position position() {
        return getPosition();
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

    protected boolean[][] stepMoves(int[][] offsets) {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        for (int[] offset : offsets) {
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