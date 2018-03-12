package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameState.StateManager.State;
import mybird.Main;

public class GameOver implements GameState {
	StateManager stateManager;
	boolean canrestart; // can hit space to restart also draw restart text
	int count; // increases every update
	int waitcount = 40; // how many count needs to be so player can restart

	GameOver(StateManager statemanager) {
		stateManager = statemanager;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// game over text
		g2d.setColor(Color.red.brighter());
		g2d.fillRect(0, 0, Main.frame.getWidth(), Main.frame.getHeight());
		Font font = new Font("my font", 8, 70);
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("game over", 220, 250);
		Font font2 = new Font("my font", 8, 30);
		g2d.setFont(font2);
		/*
		 * here below when we retrieve running state we need casting because 
		 * GameState type(which getState() method returns) isn't used to have getScore method
		 * and because we're sure that we're getting Running type instance we need to cast it
		 * to be able to use methods which Running type defines 
		 */
		g2d.drawString("your score was:" + ((Running) stateManager.getState(State.RUNNING)).getScore(), 280, 300);
		if (canrestart)
			g2d.drawString("press space to restart the game", 200, 345);

	}

	@Override
	public void update() {
		count++;
		if (count > waitcount)
			canrestart = true;
	}

	@Override
	public void keypressed(int kcode) {
		if ((canrestart) && (kcode == 32)) {
			stateManager.setState(State.RUNNING);
			count = 0;
			canrestart = false;
		}
	}

}