package game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cropper {
private BufferedImage sheet;
	
	public Cropper(String path) {
		sheet = loadImage(path);
		
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}

	public BufferedImage loadImage(String path){
		//BufferedImage image;
		try {
			BufferedImage image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null;
	}
	
}
