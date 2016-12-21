package gameState;

import java.awt.Graphics2D;

public interface GameState {
	public void draw(Graphics2D g2d);

	public void update();

	public void keypressed(int kcode);

}
