package model.pieces;

public class Knight extends Piece
{

	public Knight(int positionX, int positionY, PieceColour colour)
	{
		super(positionX, positionY, colour,"Knight");
	}

	@Override
	public boolean isValidPieceMove(int destX, int destY)
	{
		int diffrenceX = destX-getPositionX();
		int diffrenceY = destY-getPositionY();
		
		if(Math.abs(diffrenceX)==1 && Math.abs(diffrenceY)==2)
			return true;
		if(Math.abs(diffrenceX)==2 && Math.abs(diffrenceY)==1)
			return true;
		return false;
	}

}
