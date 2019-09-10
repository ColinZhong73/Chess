package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class Bishop extends Piece{

	private int value = 3;
	private BufferedImage image;
	
	public Bishop(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wBishop);
		}
		else{
			setImage(handler.getPictures().bBishop);
		}
	}
	
	protected boolean moves(Space spot1, Space spot2){
		int x = spot1.getxPos()-spot2.getxPos();
		int y = spot1.getyPos()-spot2.getyPos();
		if(Math.abs(x)==Math.abs(y)){	
			return true;
		}
		return false;
	}
	
	public boolean move(Space spot1, Space spot2){
		if(!moves(spot1, spot2)){
			return false;
		}
		
		int x1 = spot1.getxPos(), y1 = spot1.getyPos();
		int x2 = spot2.getxPos(), y2 = spot2.getyPos();
		int hori = (x1<x2) ? 1 : -1;
		int vert = (y1<y2) ? 1 : -1;
		int spacesApart = Math.abs(x1-x2);
		for(int i = 1; i < spacesApart; i++){
			if(handler.getSpace(y1+i*vert,x1+i*hori).getPiece()!=null){
				return false;
			}
		}
		
		return true;
		
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean getColor(){
		return color;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
}
