package Game;

import model.pieces.Piece;

public interface PlayerListener 
{
	public void onEndTurn() throws CloneNotSupportedException;
	public void movePiece(Piece piece , int destX,int destY);
}
