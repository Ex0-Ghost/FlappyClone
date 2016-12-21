package mybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Obstacle {
	protected BufferedImage Img;
	public int x, y, width, height;
	public boolean passed;
	public boolean visible = false;

	public void reset() {
		this.x = 800;
		this.passed = false;
		this.visible = false;
	}

	public void draw(Graphics2D g2d) {
		if (visible) {
			g2d.drawImage(Img, this.x, this.y, this.width, this.height, null);
		}
	}
}
