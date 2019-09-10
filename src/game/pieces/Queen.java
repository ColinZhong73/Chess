package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class Queen extends Piece{
	
	private int value = 9;
	int x = 33, y, image_width = 33, image_height = 33;
	private BufferedImage image;
	
	public Queen(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wQueen);
		}
		else{
			setImage(handler.getPictures().bQueen);
		}
	}
	
	protected boolean moves(Space spot1, Space spot2){
		if(spot1.getxPos()==spot2.getxPos()&&spot1.getyPos()!=spot2.getyPos()){
			return true;
		}
		else if(spot1.getxPos()!=spot2.getxPos()&&spot1.getyPos()==spot2.getyPos()){
			return true;
		}
		else if(Math.abs(spot1.getxPos()-spot2.getxPos())==Math.abs(spot1.getyPos()-spot2.getyPos())){
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
		
		if(x1!=x2&&y1!=y2){
			int hori = (x1<x2) ? 1 : -1;
			int vert = (y1<y2) ? 1 : -1;
			int spacesApart = Math.abs(x1-x2);
			for(int i = 1; i < spacesApart; i++){
				if(handler.getSpace(y1+i*vert,x1+i*hori).getPiece()!=null){
					return false;
				}
			}
		}
		else{
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
	
	/*public String getName(){
		return super.name;
	}*/
}
