package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame
{
	private BoardPanel boardPanel;

	public View()
	{
		this.setSize(722,740);
		this.setVisible(true);
		boardPanel= new BoardPanel();		
		this.getContentPane().add(boardPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public BoardPanel getBoardPanel()
	{
		return boardPanel;
	}

	public void setBoardPanel(BoardPanel boardPanel)
	{
		this.boardPanel = boardPanel;
	}
	
	
}
