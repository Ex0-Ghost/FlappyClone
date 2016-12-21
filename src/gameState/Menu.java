package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import gameState.StateManager.State;
import mybird.Main;

public class Menu implements GameState {
	StateManager stateManager;
	String[] texts = { "Resume game", "Point hack", "Credits", "mute/unmute sound", "exit" };
	Font Normal, Bold;
	int selected = 0;
	boolean inCredits = false;

	public Menu(StateManager statemanager) {
		// TODO Auto-generated constructor stub
		stateManager = statemanager;

		Normal = new Font("normal", 1, 20);
		Bold = new Font("normal", 1, 40);
	}

	void up() { // move up
		if (selected > 0) {// we aren't at the top
			selected--;
		} else if (selected == 0) { // we are at top start from bottom
			selected = texts.length - 1;
		}
	}

	void down() {
		if (selected < texts.length - 1) {// if we aren't at the end
			selected++;
		} else if (selected == texts.length - 1) { // we are at the end, start
													// from top
			selected = 0;
		}
	}

	void doSelcected() {
		switch (selected) {
		case 0:// resume
			resume();
		case 1:// point hack
			stateManager.running.score++;
			break;
		case 2:// Credits
			inCredits = true;
			break;
		case 3:
			Main.muteSwithch();
			break;
		case 4:
			System.exit(0);// i don't know, this looks like unsafe and dummy way
							// to close a program
			// just terminating JVM there will be better way of terminating
			// threads and saving stats
			break;
		}
	}

	void drawCredits(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.white);
		g2d.setFont(new Font("s", 1, 20));
		g2d.drawString("created by rekar email: rk_r93@yahoo.com for learning purposes", 10, 200);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		if (inCredits) {
			drawCredits(g2d);
		} else {
			for (int i = 0, y = 180; i < texts.length; i++, y = y + 30) {
				g2d.setColor(Color.WHITE);
				if (selected == i) { // if this one is selected
					g2d.setFont(Bold);// Bold font
				} else {// if not selected
					g2d.setFont(Normal);// normal font
				}
				g2d.drawString(texts[i], 20, y);
			}
		}
	}

	void resume() {
		stateManager.state = State.running;
	}

	@Override
	public void keypressed(int kcode) {
		// TODO Auto-generated method stub
		switch (kcode) {
		case 27: // ESC key
			if (!inCredits) {
				resume();
			} else {
				inCredits = false;
			}
			break;
		case 32: // Space key
			if (!inCredits) {
				doSelcected();
			} else {
				inCredits = false;
			}
			break;
		case 38: // Up key
			up();
			break;
		case 40: // Down key
			down();
			break;
		}
	}

}
