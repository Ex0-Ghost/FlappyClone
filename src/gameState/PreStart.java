package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameState.StateManager.State;
import mybird.Background;

public class PreStart implements GameState {
	Background background = new Background();
	StateManager stateManager;

	public PreStart(StateManager statemanager) {
		// TODO Auto-generated constructor stub
		stateManager = statemanager;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		background.draw(g2d);
		g2d.setColor(Color.white);
		g2d.setFont(new Font("s", 1, 20));
		g2d.drawString("created by rekar email: rk_r93@yahoo.com for learning purposes", 10, 200);
		g2d.setFont(new Font("s", 1, 15));
		g2d.drawString("press Space to start and jump and Q to mute or unmute sound", 20, 400);
	}

	@Override
	public void keypressed(int kcode) {
		stateManager.state = State.running;
		stateManager.running.startgame();
	}

}