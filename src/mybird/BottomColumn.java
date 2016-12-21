package mybird;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BottomColumn extends Obstacle {
	URL ImgUrl;
	public boolean passed = false;

	public BottomColumn() {
		URL imgurl = this.getClass().getResource("/mybird/resources/images/btube.png");
		try {
			Img = ImageIO.read(imgurl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = 800;
		this.y = 600;
		this.height = Img.getHeight();
		this.width = Img.getWidth();

	}

}
