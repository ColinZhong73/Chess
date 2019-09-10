package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class Rook extends Piece{
	
	private int value = 5;
	private BufferedImage image;
	private boolean firstMove;
	
	public Rook(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wRook);
		}
		else{
			setImage(handler.getPictures().bRook);
		}
		firstMove = true;
	}
	
	protected boolean moves(Space spot1, Space spot2){
		if(spot1.getxPos()==spot2.getxPos()&&spot1.getyPos()!=spot2.getyPos()){
			return true;
		}
		else if(spot1.getxPos()!=spot2.getxPos()&&spot1.getyPos()==spot2.getyPos()){
			return true;
		}
		
		return false;
	}
	
	public boolean move(Space spot1, Space spot2){
		if(!moves(spot1,spot2)){
			return false;
		}
		int x1 = spot1.getxPos(), y1 = spot1.getyPos();
		int x2 = spot2.getxPos(), y2 = spot2.getyPos();
		boolean hori = (x1!=x2) ? true : false;
		if(hori){
			int spacesApart = Math.abs(x1-x2);
			int mult = (x1 < x2) ? 1 : -1;
			for(int i = 1; i < spacesApart; i++){
				if(handler.getSpace(y1,x1+i*mult).getPiece()!=null){
					return false;
				}
			}
		}
		else{
			int spacesApart = Math.abs(y1-y2);
			int mult = (y1 < y2) ? 1 : -1;
			for(int i = 1; i < spacesApart; i++){
				if(handler.getSpace(y1+i*mult,x1).getPiece()!=null){
					return false;
				}
			}
		}
		if(firstMove){
			handler.setSpecialcase("Rook");
			firstMove = false;
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
	
	public void resetFirstMove(){
		this.firstMove = true;
	}
	
	public boolean checkFirstMove(){
		return firstMove;
	}
	
	public void firstMoved(){
		this.firstMove = false;
	}
}
