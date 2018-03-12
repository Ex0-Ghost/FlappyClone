package mybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Obstacle {
	protected BufferedImage Img;
	public int x, y, width, height;
	public boolean passed = false;
	public boolean visible = false;

	public void reset() {
		x = 800;
		passed = false;
		visible = false;
	}

	public void draw(Graphics2D g2d) {
		if (visible) {
			g2d.drawImage(Img, x, y, width, height, null);
		}
	}
}
