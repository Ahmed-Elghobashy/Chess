package Game;

import java.util.ArrayList;

import javax.print.attribute.standard.Destination;

import exceptions.NotValidMoveException;
import exceptions.NotYourTurnException;
import model.pieces.King;
import model.pieces.Piece;
import model.pieces.PieceColour;

public class Player
{
	private PieceColour colour;
	private ArrayList<Piece> pieces;
	private GameValidator validator;
	private PlayerListener listener;
	private King king;
	
	
	public Player(PieceColour colour)
	{
		pieces = new ArrayList<Piece>();
		this.colour=colour;

	}
	public void playMove(Piece piece,int destX,int destY) throws NotValidMoveException, NotYourTurnException, CloneNotSupportedException
	{
		validator.validateTurn(this);
		validator.validatePieceMove(piece, destX, destY);
		validator.validatePiecebyPass(piece, destX, destY);
	    validator.validateFriendlyPiece(piece, destX, destY);
	    validator.validateCheckMate(piece, destX, destY);
	    listener.movePiece(piece,destX,destY);
	    piece.movePiece(destX, destY);
	    listener.onEndTurn();
		
		
	}

	public int[] getKingLocation()
	{
		int[] retArray = new int[2];
		retArray[0] = getKingX();
		retArray[1] = getKingY();
		return retArray;
	}
	
	private int getKingY()
	{
		return king.getPositionY();
	}

	private int getKingX()
	{
		return getKingX();
	}

	public PieceColour getColour()
	{
		return colour;
	}

	public void setColour(PieceColour colour)
	{
		this.colour = colour;
	}

	public ArrayList<Piece> getPieces()
	{
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces)
	{
		this.pieces = pieces;
	}

	public GameValidator getValidator()
	{
		return validator;
	}

	public void setValidator(GameValidator validator)
	{
		this.validator = validator;
	}
	public King getKing()
	{
		return king;
	}
	public void setKing(King king)
	{
		this.king = king;
	}
	public PlayerListener getListener()
	{
		return listener;
	}
	public void setListener(PlayerListener listener)
	{
		this.listener = listener;
	}
	
	
}
