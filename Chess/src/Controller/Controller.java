package Controller;

import Game.Board;
import Game.GameListener;
import Game.Player;
import model.pieces.Piece;
import model.pieces.PieceColour;
import view.View;

public class Controller implements GameListener
{
	private View view;
	private Board model;
	private Piece selectedPiece;
	
	
	
	public Controller()
	{
		Player player1 = new Player(PieceColour.WHITE);
		Player player2 = new Player(PieceColour.BLACk);
		model = new Board(player1, player2);
		model.setListener(this);
		view = new View();
		updateBoard();
	}
	
	public void updateBoard()
	{
		Piece[][] board = model.getBoard();
		view.getBoardPanel().removeAll();
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(board[i][j]==null)
				{
					LocationButton button = new LocationButton(i,j);
					button.addActionListener(new LocationButtonListener(this));
					view.getBoardPanel().add(button);
				}
				else {
					LocationButton button = new LocationButton(board[i][j]);
					button.addActionListener(new LocationButtonListener(this));
					view.getBoardPanel().add(button);
				}
			}
		}
		view.revalidate();
		view.repaint();
	}
	
	public static void main(String[] args)
	{
		new Controller();
	}

	public View getView()
	{
		return view;
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public Board getModel()
	{
		return model;
	}

	public void setModel(Board model)
	{
		this.model = model;
	}

	public Piece getSelectedPiece()
	{
		return selectedPiece;
	}

	public void setSelectedPiece(Piece selectedPiece)
	{
		this.selectedPiece = selectedPiece;
	}

	@Override
	public void onGameOver()
	{
		view.dispose();
		
	}
	
	
}
