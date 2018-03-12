package mybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Background {
	private BufferedImage Img;

	private int x, y, count;

	public Background() {
		x = 0;
		y = 0;
		URL Imgurl = this.getClass().getResource("/mybird/resources/images/bg.png");
		try {
			Img = ImageIO.read(Imgurl);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void move() {
		count++;
		if (count == 2) {
			x--;
			count = 0;
		}
		if (x == -800)
			x = 0;
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(Img, x, y, Img.getWidth(), Img.getHeight(), null);
		g2d.drawImage(Img, x + 800, y, Img.getWidth(), Img.getHeight(), null);
	}
}
