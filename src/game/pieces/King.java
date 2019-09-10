package game.pieces;

import java.awt.image.BufferedImage;

import game.Handler;
import game.Space;

public class King extends Piece{
	
	private int value = 0;
	private BufferedImage image;
	private boolean firstMove;
	
	public King(Handler handler, boolean isWhite) {
		super(handler, isWhite);
		if(super.color){
			setImage(handler.getPictures().wKing);
		}
		else{
			setImage(handler.getPictures().bKing);
		}
		firstMove = true;
	}
	
	protected boolean moves(Space spot1, Space spot2){
		if(Math.abs(spot1.getxPos()-spot2.getxPos())==1&&Math.abs(spot1.getyPos()-spot2.getyPos())==1){
			return true;
		}
		if(Math.abs(spot1.getxPos()-spot2.getxPos())==1&&spot1.getyPos()==spot2.getyPos()){
			return true;
		}
		if(spot1.getxPos()==spot2.getxPos()&&Math.abs(spot1.getyPos()-spot2.getyPos())==1){
			return true;
		}
		return false;
	}
	
	public boolean move(Space spot1, Space spot2) {
		if(!moves(spot1,spot2)){
			return false;
		}
		if(firstMove){
			handler.setSpecialcase("King");
			firstMove = false;
		}
		return true;
	}
	
	public boolean castling(Space spot1, Space spot2){
		if(firstMove){
			int x1 = spot1.getxPos(), y1 = spot1.getyPos();
			int x2 = spot2.getxPos(), y2 = spot2.getyPos();
			if(y1==y2&&Math.abs(x1-x2)==2){
				if(x2 > x1){
					if(handler.getSpace(y1,x1+1).getPiece()==null&&handler.getSpace(y1,x1+2).getPiece()==null&&handler.getSpace(y1,7)!=null){
						if(handler.getSpace(y1,7).getPiece() instanceof Rook){
							Rook placeholder = (Rook) handler.getSpace(y1,7).getPiece();
							if(placeholder.checkFirstMove()){
								handler.setSpecialcase("Castle");
								firstMove = false;
								return true;
							}
						}
					}
				}
				else{
					if(handler.getSpace(y1,x1-1).getPiece()==null&&handler.getSpace(y1,x1-2).getPiece()==null
							&&handler.getSpace(y1,x1-3).getPiece()==null&&handler.getSpace(y1,0)!=null){
						if(handler.getSpace(y1,0).getPiece() instanceof Rook){
							Rook placeholder = (Rook) handler.getSpace(y1,0).getPiece();
							if(placeholder.checkFirstMove()){
								handler.setSpecialcase("Castle");
								return true;
							}
						}
					}
				}
			}
		}
		return false;
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
	
	public void resetFirstMove(){
		this.firstMove = true;
	}
	
	public BufferedImage getImage(){
		return image;
	}

	public boolean checkFirstMove() {
		return firstMove;
	}

}
