package Controller;

import javax.swing.JButton;

import model.pieces.Piece;
import model.pieces.PieceColour;

public class LocationButton extends JButton
{
	Piece piece;
	int positionX;
	int positionY;
	
	
	public LocationButton(int x, int y)
	{
		piece = null;
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setSize(150,150);
		positionX=x;
		positionY=y;
	}
	public LocationButton(Piece piece)
	{
		this.setText(piece.getName());
		this.setSize(150, 150);
		this.piece=piece;
		positionX=piece.getPositionX();
		positionY=piece.getPositionY();
	}
	public Piece getPiece()
	{
		return piece;
	}
	public void setPiece(Piece piece)
	{
		this.piece = piece;
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
	
}
