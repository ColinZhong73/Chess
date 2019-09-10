package game;

import javax.swing.JFrame;


public class Display {
	
	private final static int Width = 1200;
	private final static int Height = 800;
	private final static String title = "Chess";
	
	private JFrame frame;
	
	public Display(){
		frame = new JFrame();
		initFrame();
	}
	
	private void initFrame(){
		
		frame.setSize(Width, Height);
		frame.setLocation(5, 5);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public static int getWidth(){
		return Width;
	}
	
	public static int getHeight(){
		return Height;
	}
}