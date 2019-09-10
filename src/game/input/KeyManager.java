package game.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.Handler;
import game.Space;

public class KeyManager extends KeyAdapter{
	private Handler handler;
	
	public KeyManager(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()=='R'){
			handler.setTurn(true);
		}
		if(e.getKeyCode()=='T'){
			handler.setTurn(false);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			handler.getRecorder().undo();
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			handler.getRecorder().redo();
		}
		if(e.getKeyCode()=='F'){
			ArrayList<Space[]> moves = handler.getRecorder().getMoves();
			for(int i = 0; i < moves.size(); i++){
				System.out.print((i+1) + ". " + moves.get(i)[0].getxPos() + "," + moves.get(i)[0].getyPos());
				System.out.println(" to " + moves.get(i)[1].getxPos() + "," + moves.get(i)[1].getyPos());
			}
		}
		
		if(e.getKeyCode()=='G'){
			ArrayList<String> special= handler.getRecorder().getSpecial();
			for(int i = 0; i < special.size(); i++){
				System.out.println((i+1) + ". " + special.get(i));
			}
		}
	}
	
}
