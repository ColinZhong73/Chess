package game;

import game.graphics.PieceImage;

public class Handler {
	public Game game;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return game;
	}
	
	public Player getPlayer(boolean isWhite) {
		return game.getPlayer(isWhite);
	}

	public Board getBoard() {
		return game.getBoard();
	}
	
	public Space getSpace(int x, int y) {
		return game.getSpace(x, y);
	}

	public MoveRecorder getRecorder() {
		return game.getRecorder();
	}

	public Display getDisplay() {
		return game.getDisplay();
	}

	public PieceImage getPictures() {
		return game.getPictures();
	}

	public Space getbSpace() {
		return game.getbSpace();
	}

	public int getCaser() {
		return game.getCaser();
	}
	
	public void setCaser(int caser) {
		game.setCaser(caser);
	}

	public boolean isWhiteTurn() {
		return game.isWhiteTurn();
	}
	
	public void setTurn(boolean turn) {
		game.setTurn(turn);
	}
	
	public String getSpecialcase() {
		return game.getSpecialcase();
	}
	
	public void setSpecialcase(String sc) {
		game.setSpecialcase(sc);
	}
	
}
