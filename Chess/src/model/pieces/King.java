package model.pieces;


public class King extends Piece
{

	public King(int positionX, int positionY, PieceColour colour)
	{
		super(positionX, positionY, colour,"King");
		
	}

	@Override
	public boolean isValidPieceMove(int destX, int destY)
	{
		int diffrenceX = destX-getPositionX();
		int diffrenceY = destY-getPositionY();
		if(Math.abs(diffrenceX)<=1 && Math.abs(diffrenceY)<=1)
			return true;
		else 
			return false;
		
	}
	
	
	
	
}
