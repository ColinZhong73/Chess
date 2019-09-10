package game.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import game.Display;
import game.Handler;

public class Painter extends JComponent{
	
	private static final long serialVersionUID = -8253228704775667914L;
	private Handler handler;
	
	public Painter(Handler handler){
		this.handler = handler;
	}
	
	public void paintComponent(Graphics g){
		int width = Display.getWidth();
		int height = Display.getHeight();
		Graphics2D g2 = (Graphics2D) g;
		handler.getBoard().drawBoard(g2,width,height);
		
		g2.setColor(Color.BLACK);
		g2.drawString("Player 1: "+handler.getPlayer(true).getPoints() + " point", 800, 100);
		g2.drawString("Player 2: "+handler.getPlayer(false).getPoints() + " point", 800, 125);
	}

}
