package game;

import java.util.ArrayList;

import game.pieces.King;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.pieces.Rook;

public class MoveRecorder {
	private ArrayList<Space[]> moves = new ArrayList<Space[]>();
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private ArrayList<String> special = new ArrayList<String>();
	private Handler handler;
	private int movecount;
	
	public MoveRecorder(Handler handler){
		this.handler = handler;
		movecount = 0;
	}
	
	public void record(Space spot1, Space spot2, String condition){
		
		while(movecount<moves.size()){
			moves.remove(movecount);
		}
		while(movecount<pieces.size()){
			pieces.remove(movecount);
		}
		while(movecount<special.size()){
			special.remove(movecount);
		}
		
		Space[] movement = new Space[2];
		
		movement[0] = spot1;
		movement[1] = spot2;
		
		Piece captured = spot2.getPiece();
		
		moves.add(movement);
		pieces.add(captured);
		special.add(condition);
		
		movecount = moves.size();
		
	}
	
	public void undo(){
		if(moves.size()<=0||pieces.size()<=0||special.size()<=0||movecount<=0){
			return;
		}
		
		Space[] undoer = moves.get(movecount-1);
		handler.getBoard().movePieces(undoer[1], undoer[0]);
		undoer[1].setPiece(pieces.get(movecount-1));
		if(undoer[1].getPiece()!=null){
			if(handler.isWhiteTurn()){
				handler.getPlayer(false).revertPoints(undoer[0].getPiece().getValue());
			}
			else{
				handler.getPlayer(true).revertPoints(undoer[0].getPiece().getValue());
			}
		}
		
		if(special.get(movecount-1).equals("Pawn")){
			Pawn placeholder = (Pawn) undoer[0].getPiece();
			placeholder.resetFirstMove();
		}
		if(special.get(movecount-1).equals("EnPassant")){
			handler.getSpace(undoer[0].getyPos(),undoer[1].getxPos()).setPiece(new Pawn(handler, handler.isWhiteTurn()));
			if(handler.isWhiteTurn()){
				handler.getPlayer(false).revertPoints(1);
			}
			else{
				handler.getPlayer(true).revertPoints(1);
			}
		}
		if(special.get(movecount-1).equals("King")){
			King placeholder = (King) undoer[0].getPiece();
			placeholder.resetFirstMove();
		}
		if(special.get(movecount-1).equals("Rook")){
			Rook placeholder = (Rook) undoer[0].getPiece();
			placeholder.resetFirstMove();
		}
		if(special.get(movecount-1).equals("Castle")){
			King placeholder = (King) undoer[0].getPiece();
			placeholder.resetFirstMove();
			if(undoer[1].getxPos()>undoer[0].getxPos()){
				handler.getBoard().movePieces(handler.getSpace(undoer[0].getyPos(),5), handler.getSpace(undoer[0].getyPos(),7));
			}
			else if(undoer[1].getxPos()<undoer[0].getxPos()){
				handler.getBoard().movePieces(handler.getSpace(undoer[0].getyPos(),3), handler.getSpace(undoer[0].getyPos(),0));
			}
		}
		
		movecount--;
		
		handler.setTurn(!handler.isWhiteTurn());
		if(handler.getCaser()!=1){
			handler.setCaser(1);
			handler.getbSpace().setColor(((handler.getbSpace().getxPos()+handler.getbSpace().getyPos())%2==0) ? handler.getBoard().getColor1() : handler.getBoard().getColor2());
		}
		
	}
	
	public void redo(){
		if(movecount>=moves.size()||movecount>=pieces.size()||movecount>=special.size()){
			return;
		}
		
		Space[] redoer = moves.get(movecount);
		if(redoer[1].getPiece()!=null){
			if(!handler.isWhiteTurn()){
				handler.getPlayer(false).addPoints(redoer[0].getPiece().getValue());
			}
			else{
				handler.getPlayer(true).addPoints(redoer[0].getPiece().getValue());
			}
		}
		handler.getBoard().movePieces(redoer[0], redoer[1]);
		
		movecount++;
		
		handler.setTurn(!handler.isWhiteTurn());
		if(handler.getCaser()!=1){
			handler.setCaser(1);
			handler.getbSpace().setColor(((handler.getbSpace().getxPos()+handler.getbSpace().getyPos())%2==0) ? handler.getBoard().getColor1() : handler.getBoard().getColor2());
		}
		
	}
	
	public int getMovecount(){
		return movecount;
	}
	
	public ArrayList<Space[]> getMoves(){
		return moves;
	}
	
	public ArrayList<String> getSpecial(){
		return special;
	}
	
}
