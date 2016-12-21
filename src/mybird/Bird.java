package mybird;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Bird {
	public double theta;
	private BufferedImage bird, bird1, bird2, bird3; // bird is a pointer points to
												// current bird frame others are
												// the frames
	public int x, y, width, height, yv = 0, f = 0;// f is for frame update
													// purposes see update()
	AffineTransform at = new AffineTransform();

	public Bird() {
		theta = Math.toRadians(-10);

		URL url1 = this.getClass().getResource("/mybird/resources/images/bird1.png");
		URL url2 = this.getClass().getResource("/mybird/resources/images/bird2.png");
		URL url3 = this.getClass().getResource("/mybird/resources/images/bird3.png");
		try {
			bird1 = ImageIO.read(url1);
			bird2 = ImageIO.read(url2);
			bird3 = ImageIO.read(url3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bird = bird1;
		this.x = 280;
		this.y = 280;
		this.height = bird.getHeight();
		this.width = bird.getWidth();
		// set the frame
	}

	public void update() {
		if (!(theta > 0.8)) {
			theta = Math.toRadians((Math.toDegrees(theta) + 3));
		}
		f++;
		switch (f) {
		case 0:
			bird = bird1;
			break;
		case 3:
			bird = bird2;
			break;
		case 6:
			bird = bird3;
			break;
		case 8:
			f = 0;

		}

	}

	public void draw(Graphics2D g2d) {
		g2d.rotate(theta);
		// rotate will rotate g2d around 0,0 of screen to draw the bird in right
		// position we need to apply rotation matrix to x and y with reverse
		// degrees
		// see rotate method and affinetransform to see how this works
		g2d.drawImage(bird, (int) (x * Math.cos(-theta) + (-y * Math.sin(-theta))),
				(int) (x * Math.sin(-theta) + (y * Math.cos(-theta))), width, height, null);
		// g2d.drawImage(bird, x, y, null); //this is rotated bird around 0,0 of
		// screen
		g2d.rotate(-theta); // rotate g2d to its original state
		// g2d.drawRect(x, y, width, height); //draw a rect to show the bird hit box
		// note the bird's hit box will be diffrent from bird image
	}

	public boolean coll(Obstacle c) { // bird collision detection with an column
									// this is my method of doing that. see if
									// you have better one.
		int tx = this.x + this.width;
		int ty = this.y + this.height;
		int cx = c.x + c.width;
		int cy = c.y + c.height;
		return ((tx > c.x && ty > c.y) && (cx > this.x && cy > this.y));
	}

}
