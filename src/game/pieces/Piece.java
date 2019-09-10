package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public abstract class Piece {
	
	private int value;
	protected boolean color;
	protected Handler handler;
	private BufferedImage image;
	
	public Piece(Handler handler, boolean isWhite){
		this.handler = handler;
		this.color = isWhite;
	}
	
	protected abstract boolean moves(Space spot1, Space spot2);
	
	public abstract boolean move(Space spot1, Space spot2);
	
	public int getValue(){
		return value;
	}

	public boolean getColor(){
		return color;
	}
	
	abstract public void setImage(BufferedImage image);
	
	public BufferedImage getImage(){
		return image;
	}
	
}
