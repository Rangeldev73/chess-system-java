package boardgame;

public abstract class Piece {

    private Position position;
    private final Board board;

    public Piece(Board board){
        this.board = board;
        this.position = null;
    }

    protected Board getBoard(){
        return board;
    }

    protected Position getPosition(){
        return position;
    }

    protected void setPosition(Position position){
        this.position = position;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        if (!getBoard().positionExists(position)) {
            return false;
        }
        return possibleMoves()[position.row()][position.column()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] possibleMoves = possibleMoves();
        for(int i = 0; i < possibleMoves.length; i++){
            for(int j = 0; j < possibleMoves[i].length; j++){
                if (possibleMoves[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}