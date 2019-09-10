package game;

import java.awt.Graphics;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Pawn;
import game.pieces.Piece;
import game.pieces.Queen;
import game.pieces.Rook;
import game.utils.Utils;

public class Board {
	
	private Space[][] board = new Space[8][8];
	private int color1;
	private int color2;
	private int color3;
	private int xOffset;
	private int yOffset;
	private Handler handler;
		
	public Board(Handler handler, int color1, int color2){
		this.handler = handler;
		this.color1 = color1;
		this.color2 = color2;
		calcColors();
		loadBoard("Res/Boards/Default.txt");
		initBoard();
	}
	
	public Board(Handler handler, int color1, int color2, String path){
		path = "Res/Boards/" + path;
		this.handler = handler;
		this.color1 = color1;
		this.color2 = color2;
		calcColors();
		loadBoard(path);
		initBoard();
	}
	
	private void initBoard(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j< 8; j++){
				if((i+j)%2==0){
					board[i][j].setColor(color1);
				}
				else{
					board[i][j].setColor(color2);
				}
			}
		}
	}
	
	private void calcColors(){
		if(color1==color2){
			color2++;
		}
		color3 = (color1 > color2) ? color1 : color2;
		color3++;
		if(color3 > 9){
			if(color1 == 0){
				color3 = 1;
			}
			else{
				color3 = 0;
			}
		}
	}
	
	public void drawBoard(Graphics g, int width, int height){
		xOffset = 0;//calcOffset(width);
		yOffset = 0;//calcOffset(height);
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				board[i][j].drawSpace(g, xOffset, yOffset);
			}
		}
	}
	
	public void movePieces(Space spot1, Space spot2){
		Piece temp = spot1.getPiece();
		spot1.setPiece(null);
		spot2.setPiece(temp);
	}
	
	public int calcOffset(int offset){
		return (offset-board[0][0].getSize()*8)/2;
	}
	
	public Space getSpace(int x, int y){
		return board[x][y];
	}
	
	public int getColor1(){
		return color1;
	}
	
	public int getColor2(){
		return color2;
	}
	
	public int getColor3(){
		return color3;
	}
	
	public void loadBoard(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\n");
		String[][] newTokens = new String[tokens.length][];
		for(int i = 0; i < tokens.length; i++){
			newTokens[i] = tokens[i].split("\\s+");
		}
		for(int y = 0; y < 8; y++){
			for(int x = 0; x < 8; x++){
				try{
					switch(newTokens[y][x].charAt(0)){
						case 'P': board[y][x] = new Space(x, y, new Pawn(handler, true)); break;
						case 'R': board[y][x] = new Space(x, y, new Rook(handler, true)); break;
						case 'B': board[y][x] = new Space(x, y, new Bishop(handler, true)); break;
						case 'K': board[y][x] = new Space(x, y, new Knight(handler, true)); break;
						case 'Q': board[y][x] = new Space(x, y, new Queen(handler, true)); break;
						case 'C': board[y][x] = new Space(x, y, new King(handler, true)); break;
						
						case 'p': board[y][x] = new Space(x, y, new Pawn(handler, false)); break;
						case 'r': board[y][x] = new Space(x, y, new Rook(handler, false)); break;
						case 'b': board[y][x] = new Space(x, y, new Bishop(handler, false)); break;
						case 'k': board[y][x] = new Space(x, y, new Knight(handler, false)); break;
						case 'q': board[y][x] = new Space(x, y, new Queen(handler, false)); break;
						case 'c': board[y][x] = new Space(x, y, new King(handler, false)); break;
						
						default: board[y][x] = new Space(x, y, null); break;
					}
				}
				catch(IndexOutOfBoundsException e) {
					board[y][x] = new Space(x, y, null);
				}
			}
		}
	}
	
}