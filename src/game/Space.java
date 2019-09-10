package game;

import java.awt.Color;
import java.awt.Graphics;

import game.pieces.Piece;

public class Space {
	private int xPos, yPos;
	private int color = 1;
	private final int size = 64;
	private Piece piece;
	
	public Space(int xPos, int yPos, Piece piece){
		this.xPos = xPos;
		this.yPos = yPos;
		this.piece = piece;
	}
	
	public void drawSpace(Graphics g, int xOffset, int yOffset){
		g.setColor(translateColor(color));
		g.fillRect(xPos*size+xOffset, yPos*size+yOffset, size, size);
		if(piece != null){
			g.drawImage(piece.getImage(), xPos*size+xOffset, yPos*size+yOffset, size, size, null);
		}
	}
	
	private Color translateColor(int color){
		Color c;
		switch(color){
			case 1: c = new Color(0,0,0);break;//black
			case 2: c = new Color(255,255,255);break;//white
			case 3: c = new Color(255,0,0);break;//red
			case 4: c = new Color(0,255,0);break;//green
			case 5: c = new Color(0,0,255);break;//blue
			case 6: c = new Color(255,255,0); break;//yellow
			case 7: c = new Color(0,255,255); break;//aqua
			case 8: c = new Color(255,0,255); break;//light_purple
			case 9: c = new Color(192,192,192); break;//silver
			default: c = new Color(255,255,255); break;//white
		}
		return c;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int getxPos(){
		return xPos;
	}

	public int getyPos(){
		return yPos;
	}

	public Piece getPiece(){
		return piece;
	}

	public void setPiece(Piece piece){
		this.piece = piece;
	}
	
	public int getSize(){
		return size;
	}
}