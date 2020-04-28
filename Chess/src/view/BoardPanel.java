package view;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
	public BoardPanel()
	{
		this.setLayout(new GridLayout(8,8));
		this.setSize(722,740);
	}
	
	public void paintComponent(Graphics page)
	{
	    super.paintComponent(page);
	    Image background = new ImageIcon("images/chess_background.gif").getImage();
	    page.drawImage(background, 0, 0, null);
	}
}
