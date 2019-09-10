package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class Knight extends Piece{
	
	private int value = 3;
	private BufferedImage image;
	
	public Knight(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wKnight);
		}
		else{
			setImage(handler.getPictures().bKnight);
		}
	}
	
	protected boolean moves(Space spot1, Space spot2){
		if(Math.abs(spot1.getxPos()-spot2.getxPos())==2&&Math.abs(spot1.getyPos()-spot2.getyPos())==1){
			return true;
		}
		else if(Math.abs(spot1.getxPos()-spot2.getxPos())==1&&Math.abs(spot1.getyPos()-spot2.getyPos())==2){
			return true;
		}
		return false;
	}
	
	public boolean move(Space spot1, Space spot2) {
		return moves(spot1, spot2);
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
