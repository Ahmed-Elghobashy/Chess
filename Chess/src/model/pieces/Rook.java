package model.pieces;

public class Rook extends Piece
{

	public Rook(int positionX, int positionY, PieceColour colour)
	{
		super(positionX, positionY, colour,"rook");
	}

	@Override
	public boolean isValidPieceMove(int destX, int destY)
	{
		if(isLinearMove(destX, destY))
			return true;
		else 
			return false;
	}

}
