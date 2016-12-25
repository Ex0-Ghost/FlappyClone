package gameState;

import java.awt.Graphics2D;

interface GameState {
	void draw(Graphics2D g2d);

	void update();

	void keypressed(int kcode);

}
