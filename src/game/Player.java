package game;

import javax.swing.JOptionPane;

import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.pieces.Queen;
import game.pieces.Rook;

public class Player {
	private int points;
	private boolean isP1;
	private Handler handler;
	
	public Player(Handler handler, boolean isWhite){
		this.handler = handler;
		this.isP1 = isWhite;
		this.points = 0;
	}
	
	public boolean choosePiece(Space space){
		if(space.getPiece()!=null){
			if(space.getPiece().getColor()==isP1){
				return true;
			}
		}
		return false;
	}
	
	public boolean chooseSpot(Space space){
		if(space.getPiece()==null){
			return true;
		}
		else if(space.getPiece().getColor()!=isP1){
			return true;
		}
		return false;
	}
	
	public boolean checkMoveable(Space spot1, Space spot2){
		if(spot1.getPiece().move(spot1, spot2)){
			if(spot1.getPiece() instanceof Pawn){
				Pawn placeholder = (Pawn) spot1.getPiece();
				if(placeholder.promotion(spot2)){
					promotePawn(spot1);
				}
			}
			return true;
		}
		else if(spot1.getPiece() instanceof Pawn){
			Pawn placeholder = (Pawn) spot1.getPiece();
			if(placeholder.capture(spot1, spot2)){
				if(placeholder.promotion(spot2)){
					promotePawn(spot1);
				}
				return true;
			}
			else if(placeholder.enPassant(spot1, spot2)){
				points++;
				handler.getSpace(spot1.getyPos(),spot2.getxPos()).setPiece(null);
				return true;
			}
		}
		else if(spot1.getPiece() instanceof King){
			King placeholder = (King) spot1.getPiece();
			if(placeholder.castling(spot1, spot2)){
				int y = spot1.getyPos();
				if(spot2.getxPos()>spot1.getxPos()){
					handler.getBoard().movePieces(handler.getSpace(y,7), handler.getSpace(y,5));
				}
				else{
					handler.getBoard().movePieces(handler.getSpace(y,0), handler.getSpace(y,3));
				}
				return true;
			}
		}
		return false;
	}
	
	public void promotePawn(Space spot){
		Object[] pieces = {"Queen", "Knight", "Rook", "Bishop"};
		String promotedTo = (String) JOptionPane.showInputDialog(handler.getDisplay().getFrame(), 
				"Which Piece? (Cannot Cancel)", "Choose your piece", JOptionPane.PLAIN_MESSAGE, null, pieces, null);
		try{
			if(promotedTo.equals("Queen")){
				Piece queen = new Queen(handler, isP1);
				spot.setPiece(queen);
			}
			else if(promotedTo.equals("Knight")){
				Piece knight = new Knight(handler, isP1);
				spot.setPiece(knight);
			}
			else if(promotedTo.equals("Rook")){
				Piece rook = new Rook(handler, isP1);
				spot.setPiece(rook);
			}
			else if(promotedTo.equals("Bishop")){
				Piece bishop = new Bishop(handler, isP1);
				spot.setPiece(bishop);
			}
			else{
				promotePawn(spot);
			}
			
		}
		catch(NullPointerException e){
			promotePawn(spot);
		}
	}
	
	public void checkIsInCheck(){
		
		
	}
	
	public int getPoints(){
		return points;
	}

	public void setPoints(int points){
		this.points = points;
	}
	
	public void addPoints(int points){
		this.points += points;
	}
	
	public void revertPoints(int points){
		this.points -= points;
	}
	
	public boolean getIsP1(){
		return isP1;
	}
	
}