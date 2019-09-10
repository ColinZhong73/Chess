package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class Pawn extends Piece{
	
	private int value = 1;
	private BufferedImage image;
	private boolean firstMove;
	private int mult;
	
	public Pawn(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wPawn);
		}
		else{
			setImage(handler.getPictures().bPawn);
		}
		mult = color ? 1 : -1;
		firstMove = true;
	}
	
	protected boolean moves(Space spot1, Space spot2){
		if(spot1.getxPos()==spot2.getxPos()){
			if(firstMove==true&&spot1.getyPos()-spot2.getyPos()==(mult*2)){
				return true;
			}
			if(spot1.getyPos()-spot2.getyPos()==mult){
				return true;
			}
		}
		return false;
	}
	
	public boolean move(Space spot1, Space spot2){
		if(!moves(spot1, spot2)){
			return false;
		}
		int x = spot1.getxPos(), y1 = spot1.getyPos(), y2 = spot2.getyPos();
		if(Math.abs(y1-y2)==2){
			if(handler.getSpace((y1+y2)/2,x).getPiece()!=null){
				return false;
			}
		}
		if(handler.getSpace(y2,x).getPiece()!=null){
			return false;
		}
		if(firstMove){
			handler.setSpecialcase("Pawn");
			firstMove = false;
		}
		return true;
	}
	
	public boolean capture(Space spot1, Space spot2){
		if(spot2.getPiece()!=null){
			int x1 = spot1.getxPos(), y1 = spot1.getyPos();
			int x2 = spot2.getxPos(), y2 = spot2.getyPos();
			if(Math.abs(x1-x2)==1&&(y1-y2)==mult){	
				if(spot2.getPiece().color!=color){
					if(firstMove){
						handler.setSpecialcase("Pawn");
						firstMove=false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean enPassant(Space spot1, Space spot2){
		int x1 = spot1.getxPos(), y1 = spot1.getyPos();
		int x2 = spot2.getxPos(), y2 = spot2.getyPos();
		if(Math.abs(x1-x2)==1&&(y1-y2)==mult){
			if((y1==3&&color)||(y1==4)&&!color){
				if(handler.getSpace(y1,x2).getPiece()!=null){
					if(handler.getSpace(y1,x2).getPiece() instanceof Pawn){
						Space s1 = handler.getRecorder().getMoves().get(handler.getRecorder().getMovecount()-1)[0];
						Space s2 = handler.getRecorder().getMoves().get(handler.getRecorder().getMovecount()-1)[1];
						if((Math.abs(s2.getyPos()-s1.getyPos())==2)&&s2.getxPos()==x2&&s2.getyPos()==y1){
							handler.setSpecialcase("EnPassant");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean promotion(Space spot){
		return (((mult==1)&&(spot.getyPos()==0))||((mult==-1)&&(spot.getyPos()==7)));
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean getColor(){
		return color;
	}
	
	public void resetFirstMove(){
		this.firstMove = true;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
}
