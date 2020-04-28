package model.pieces;

public class Pawn extends Piece
{
	private boolean notPlayed;
	
	public Pawn(int x , int y,PieceColour colour)
	{
		super(x, y,colour,"pawn");
		notPlayed=true;
	}
	
	public void attack()
	{
		movePiece(getPositionX()+1, getPositionY()+1);
	}
	
	public void move()
	{
		movePiece(getPositionX(), getPositionY()+1);
	}

	@Override
	public boolean isValidPieceMove( int destX,int destY)
	{
		int diffrenceX = (destX-getPositionX());
		int diffrenceY = Math.abs(destY-getPositionY());
		if(getColour()==PieceColour.WHITE)
		{	
			if(notPlayed)
			{
				if( (diffrenceX==1 &&  diffrenceY==0) || (diffrenceX==1 && diffrenceY==1)  || (diffrenceX==2 &&  diffrenceY==0))
					return true;
				else 
					return false;
			}
			if( (diffrenceX==1 &&  diffrenceY==0) || (diffrenceX==1 && diffrenceY==1))
				return true;
			else 
				return false;
			}
		else
		{
			if(notPlayed)
			{
				if( (diffrenceX==-1 &&  diffrenceY==0) || (diffrenceX==-1 && diffrenceY==1)  || (diffrenceX==-2 &&  diffrenceY==0))
					return true;
				else 
					return false;
			}
			if( (diffrenceX==-1 &&  diffrenceY==0) || (diffrenceX==-1 && diffrenceY==1))
				return true;
			else 
				return false;
			}
		
		
		}

	public boolean isNotPlayed()
	{
		return notPlayed;
	}

	public void setNotPlayed(boolean notPlayed)
	{
		this.notPlayed = notPlayed;
	}

	
	
	
	

}
