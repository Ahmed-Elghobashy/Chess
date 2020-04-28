package Game;

import exceptions.NotValidMoveException;
import exceptions.NotYourTurnException;
import model.pieces.Pawn;
import model.pieces.Piece;

public interface GameValidator
{
	public void validateFriendlyPiece(Piece piece,int destX,int destY) throws NotValidMoveException;
	public void validateCheckMate(Piece piece,int destX,int destY) throws NotValidMoveException, CloneNotSupportedException;
	public void validatePiecebyPass(Piece piece,int destX,int destY) throws NotValidMoveException;
	public void validatePieceMove(Piece piece,int destX,int destY) throws  NotValidMoveException;
	public void validateTurn(Player player) throws NotYourTurnException;
	public void validatePawnMove(Pawn pawn , int destX , int destY);
}
