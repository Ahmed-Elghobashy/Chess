package Controller;

import javax.swing.ImageIcon;

import Game.Board;
import Game.GameListener;
import Game.Player;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.PieceColour;
import model.pieces.Queen;
import model.pieces.Rook;
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
					selectImage(button);
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
	
	public void selectImage(LocationButton button)
	{
		Piece piece = button.getPiece();
		button.setText("");
		button.setBorder(null);
		button.setBorderPainted(false);
		if(piece instanceof Pawn)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/pawn_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/pawn_black.png"));
			
		}
		if(piece instanceof King)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/king_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/king_black.png"));
			
		}
		if(piece instanceof Queen)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/queen_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/queen_black.png"));
			
		}
		if(piece instanceof Bishop)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/bishop_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/bishop_black.png"));
			
		}
		if(piece instanceof Knight)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/knight_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/knight_black.png"));
			
		}
		if(piece instanceof Rook)
		{
			if(piece.getColour()==PieceColour.WHITE)
			  button.setIcon(new ImageIcon("pieces/rook_white.png"));
			 else
				 button.setIcon(new ImageIcon("pieces/rook_black.png"));
			
		}
	}
	
	
}
