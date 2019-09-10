package game;

import game.graphics.Painter;
import game.graphics.PieceImage;
import game.input.KeyManager;
import game.input.MouseManager;

public class Game {
	private Handler handler;
	private Player player1;
	private Player player2;
	private Board board;
	private MoveRecorder recorder;
	
	private Display display;
	private Painter painter;
	private PieceImage pictures;
	private MouseManager mouser;
	private KeyManager keyer;
	
	public static void main(String[] args){
		Game game = new Game();
		game.run();
	}
	
	public Game(){
		initGame();
	}
	
	private void initGame(){
		handler = new Handler(this);
		pictures = new PieceImage();
		board = new Board(handler, 2, 6);
		display = new Display();
		painter = new Painter(handler);
		player1 = new Player(handler, true);
		player2 = new Player(handler, false);
		recorder = new MoveRecorder(handler);
		mouser = new MouseManager(handler);
		keyer = new KeyManager(handler);
		
		display.getFrame().add(painter);
		display.getFrame().addMouseListener(mouser);
		display.getFrame().addKeyListener(keyer);
	}
	
	private void run(){
		long timer = System.currentTimeMillis();
		while(true){
			if(System.currentTimeMillis()-timer>=(1000/60)){
				display.getFrame().repaint();
				timer = System.currentTimeMillis();
			}
		}
	}
	
	private Space bSpace;
	private int caser = 1;
	private boolean whiteTurn = true;
	private String specialcase = "";
	
	public void callAction(int x, int y){
		Space space = board.getSpace(y,x);
		Player p = ((whiteTurn == true) ? player1 : player2);
		switch(caser){
		case 1: if(p.choosePiece(space)){
					bSpace = space;
					bSpace.setColor(board.getColor3());
					caser++;
				}
				break;

		case 2: if(p.chooseSpot(space)){
					if(player1.checkMoveable(bSpace, space)){
						recorder.record(bSpace, space, specialcase);
						specialcase = "";
						if(space.getPiece()!=null){
							p.addPoints(space.getPiece().getValue());
						}
						board.movePieces(bSpace, space);
						bSpace.setColor(((bSpace.getxPos()+bSpace.getyPos())%2==0) ? board.getColor1() : board.getColor2());
						whiteTurn = !whiteTurn;
						caser--;
					}
				}
				else{
					bSpace.setColor(((bSpace.getxPos()+bSpace.getyPos())%2==0) ? board.getColor1() : board.getColor2());
					caser--;
					callAction(x,y);
				}
				break;
		default: 
			System.out.println("Error");
			caser = 1;
			callAction(x,y);
		}
	}

	public Player getPlayer(boolean isWhite) {
		if(!isWhite){
			return player2;
		}
		return player1;
	}

	public Board getBoard() {
		return board;
	}
	
	public Space getSpace(int x, int y) {
		return board.getSpace(x, y);
	}

	public MoveRecorder getRecorder() {
		return recorder;
	}

	public Display getDisplay() {
		return display;
	}

	public PieceImage getPictures() {
		return pictures;
	}

	public Space getbSpace() {
		return bSpace;
	}

	public int getCaser() {
		return caser;
	}

	public void setCaser(int caser) {
		this.caser = caser;
	}
	
	public boolean isWhiteTurn() {
		return whiteTurn;
	}
	
	public void setTurn(boolean turn) {
		whiteTurn = turn;
	}
	
	public String getSpecialcase() {
		return specialcase;
	}
	
	public void setSpecialcase(String sc) {
		this.specialcase = sc;
	}
	
}