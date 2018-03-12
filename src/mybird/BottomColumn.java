package mybird;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BottomColumn extends Obstacle {

	public BottomColumn() {
		URL imgurl = this.getClass().getResource("/mybird/resources/images/btube.png");
		try {
			Img = ImageIO.read(imgurl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		x = 800;
		y = 600;
		height = Img.getHeight();
		width = Img.getWidth();

	}

}
