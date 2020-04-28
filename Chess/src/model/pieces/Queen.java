package model.pieces;

public class Queen extends Piece
{

	public Queen(int positionX, int positionY, PieceColour colour)
	{
		super(positionX, positionY, colour,"queen");
		
	}

	@Override
	public boolean isValidPieceMove(int destX, int destY)
	{
		if(isLateralMove(destX, destY) || isLinearMove(destX, destY))
			return true;
		return false;
	}

}
