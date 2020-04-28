package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Game.Player;
import exceptions.NotValidMoveException;
import exceptions.NotYourTurnException;
import model.pieces.Piece;
import model.pieces.PieceColour;

public class LocationButtonListener implements ActionListener
{
	private Controller controller;
	
	public LocationButtonListener(Controller controller)
	{
		this.controller=controller;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof LocationButton)
		{
			LocationButton button =(LocationButton) e.getSource();
			int destX = button.getPositionX();
			int destY = button.getPositionY();
			Piece selectedPiece = controller.getSelectedPiece();
			Player currPlayer = controller.getModel().getCurretPlayer();
			PieceColour currentColour = currPlayer.getColour();
			if(selectedPiece == null && button.getPiece()!=null )
			{
			   if(button.getPiece().getColour()==currentColour)	
				{
				   controller.setSelectedPiece(button.getPiece());
				   controller.updateBoard();
				}
			}
			else
			{
			 if(selectedPiece == button.getPiece())
			 {
				 controller.setSelectedPiece(null);
				 controller.updateBoard();
			 }
			 else
			 {
				 try
				{
					currPlayer.playMove(selectedPiece, destX, destY);
					controller.setSelectedPiece(null);
					controller.updateBoard();
				} catch (NotValidMoveException | NotYourTurnException | CloneNotSupportedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1 instanceof NotValidMoveException)
					{
						String message = "canot move from (" +selectedPiece.getPositionX()+","+selectedPiece.getPositionY()+")" +" to (" + destX + "," + destY +")";
						JOptionPane.showMessageDialog(controller.getView(),message);
						controller.setSelectedPiece(null);
					}
				}
		     }
			}
		}
		
	}
}
