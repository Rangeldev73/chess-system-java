package boardgame;

public class Board {

    private final int rows;
    private final int columns;
    private final Piece[][] pieces;

    public Board(int rows, int columns){
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }
    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Piece piece(int row, int column){
        validatePositionExists(row, column);
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return piece(position.row(), position.column());
    }

    public void placePiece(Piece piece, Position position){
        if (piece == null) {
            throw new BoardException("Cannot place a null piece on the board");
        }
        validatePositionExists(position);
        if (thereIsAPiece(position.row(), position.column())) {
            throw new BoardException("There is already a piece on position " + position);
        }
        pieces[position.row()][position.column()] = piece;
        piece.setPosition(position);
    }

    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean positionExists(Position position){
        return positionExists(position.row(), position.column());
    }

    private void validatePositionExists(int row, int column) {
        if (!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
    }

    private void validatePositionExists(Position position) {
        validatePositionExists(position.row(), position.column());
    }

    private boolean thereIsAPiece(int row, int column) {
        return pieces[row][column] != null;
    }

    public boolean thereIsAPiece(Position position) {
        validatePositionExists(position);
        return thereIsAPiece(position.row(), position.column());
    }

    public Piece removePiece(Position position) {
        validatePositionExists(position);

        int row = position.row();
        int column = position.column();

        if (!thereIsAPiece(row, column)) {
            return null;
        }

        Piece removedPiece = pieces[row][column];
        pieces[row][column] = null;
        removedPiece.setPosition(null);

        return removedPiece;
    }
}