package game.graphics;

import java.awt.image.BufferedImage;

public class PieceImage {
	
	public BufferedImage wPawn, wKnight, wBishop, wRook, wQueen, wKing, bPawn, bKnight, bBishop, bRook, bQueen, bKing;
	private final double size = 83.33;
	
	public PieceImage(){
		initImages();
	}
	
	public void initImages(){
		Cropper cropper = new Cropper("Res/Images/ChessSprites.png");
		
		wPawn = cropper.crop((int) size*5, (int) size*0, (int) size, (int) size);
		wKnight = cropper.crop((int) size*3, (int) size*0, (int) size, (int) size);
		wBishop = cropper.crop((int) size*2, (int) size*0, (int) size, (int) size);
		wRook = cropper.crop((int) size*4, (int) size*0, (int) size, (int) size);
		wQueen = cropper.crop((int) size*1, (int) size*0, (int) size, (int) size);
		wKing = cropper.crop((int) size*0, (int) size*0, (int) size, (int) size);
		
		bPawn = cropper.crop((int) size*5, (int) size*1, (int) size, (int) size);
		bKnight = cropper.crop((int) size*3, (int) size*1, (int) size, (int) size);
		bBishop = cropper.crop((int) size*2, (int) size*1, (int) size, (int) size);
		bRook = cropper.crop((int) size*4, (int) size*1, (int) size, (int) size);
		bQueen = cropper.crop((int) size*1, (int) size*1, (int) size, (int) size);
		bKing = cropper.crop((int) size*0, (int) size*1, (int) size, (int) size);
	}
	
}
