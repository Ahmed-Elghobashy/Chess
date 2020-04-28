package model.pieces;




public abstract class Piece implements Cloneable
{
	private int positionX;
	private int positionY;
	private PieceColour colour;
	private String name;
	
	


	public Piece(int positionX, int positionY,PieceColour colour,String name)
	{
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.colour=colour;
		this.name=name;
	}
	
	
	public int getPositionX()
	{
		return positionX;
	}
	public void setPositionX(int positionX)
	{
		this.positionX = positionX;
	}
	public int getPositionY()
	{
		return positionY;
	}
	public void setPositionY(int positionY)
	{
		this.positionY = positionY;
	}
	
	public void movePiece(int destX,int destY)
	{
		positionX = destX;
		positionY = destY;
	}
	public boolean isVerticalMovement(int destX,int destY)
	{
		int diffrenceX = Math.abs(destX-positionX);
		int diffrenceY = Math.abs(destY-positionY);
		
		if(diffrenceX!=0 && diffrenceY==0)
			return true;
		return  false;

	}
	
	public boolean isHorizontalMovement(int destX,int destY)
	{
		int diffrenceX =Math.abs(destX-positionX);
		int diffrenceY =Math.abs(destY-positionY);
		
		if(diffrenceX==0 && diffrenceY!=0)
			return true;
		return false;

	}
	
	public PieceColour getColour()
	{
		return colour;
	}


	public void setColour(PieceColour colour)
	{
		this.colour = colour;
	}


	public abstract boolean isValidPieceMove(int destX,int destY);
	
	public boolean isLateralMove(int destX,int destY)
	{
		int diffrenceX = Math.abs(destX-positionX);
		int diffrenceY = Math.abs(destY-positionY);
		if(diffrenceX==diffrenceY)
			return true;
		else 
			return false;
	}
	
	public boolean isLinearMove(int destX,int destY)
	{
		int diffrenceX =Math.abs(destX-positionX);
		int diffrenceY =Math.abs(destY-positionY);
		
		if(diffrenceX==0 && diffrenceY!=0)
			return true;
		if(diffrenceY==0 && diffrenceX!=0)
			return true;
		return false;
	}
	
	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}

	
	public Piece clone() throws CloneNotSupportedException
	{
		
		return (Piece) super.clone();
	}

}
