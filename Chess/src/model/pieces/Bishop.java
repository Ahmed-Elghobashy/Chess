package model.pieces;

public class Bishop extends Piece
{

	public Bishop(int positionX, int positionY, PieceColour colour)
	{
		super(positionX, positionY, colour,"bishop");
	}

	@Override
	public boolean isValidPieceMove(int destX, int destY)
	{

		if(isLateralMove(destX, destY))
			return true;
		else 
			return false;
	}

}
