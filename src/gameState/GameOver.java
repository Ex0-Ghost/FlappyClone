package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import mybird.Main;

public class GameOver implements GameState {
	StateManager stateManager;
	boolean canrestart; // can hit space to restart also draw restart text
	int count; // increases every update
	int waitcount = 40; // how many count need to be so player can restart

	public GameOver(StateManager statemanager) {
		// TODO Auto-generated constructor stub
		stateManager = statemanager;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		// game over text
		g2d.setColor(Color.red.brighter());
		g2d.fillRect(0, 0, Main.frame.getWidth(), Main.frame.getHeight());
		Font font = new Font("my font", 8, 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("game over", 220, 250);
		Font font2 = new Font("my font", 8, 30);
		g2d.setFont(font2);
		g2d.drawString("your score was:" + stateManager.running.score, 280, 300);
		if (canrestart)
			g2d.drawString("press space to restart the game", 200, 345);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		count++;
		if (count > waitcount)
			canrestart = true;
	}

	@Override
	public void keypressed(int kcode) {
		// TODO Auto-generated method stub
		if ((canrestart) && (kcode == 32)) {
			stateManager.running.startgame();
			count = 0;
			canrestart = false;
		}
	}

}