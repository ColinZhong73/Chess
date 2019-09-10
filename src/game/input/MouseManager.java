package game.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Handler;

public class MouseManager extends MouseAdapter{
	private int x;
    private int y;
    private final int xDisp = 3;
    private final int yDisp = 25; 
	private final int size = 64;
	private Handler handler;
    
	public MouseManager(Handler handler){
		this.handler = handler;
		this.x = 0;
	    this.y = 0;
	}
	
	public void mouseReleased(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		x = (x-xDisp)/size;
		y = (y-yDisp)/size;
		if(x < 0){
			x = 0;
		}
		if(x > 7){
			x = 7;
		}
		if(y < 0){
			y = 0;
		}
		
		if(y > 7){
			y = 7;
		}
		handler.getGame().callAction(x,y);
	}
	
}