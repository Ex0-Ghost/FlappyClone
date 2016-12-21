package gameState;

import java.awt.Graphics2D;

public class StateManager {
	public enum State {
		notstarted, running, menu, over
	}

	public State state;
	public Menu menu;
	public PreStart notstarted;
	public GameOver over;
	public Running running;

	public StateManager() {
		menu = new Menu(this);
		notstarted = new PreStart(this);
		over = new GameOver(this);
		running = new Running(this);
		state = State.notstarted;
	}

	public void keypressed(int kcode) {
		switch (state) {
		case notstarted:
			notstarted.keypressed(kcode);
			;
			break;
		case running:
			running.keypressed(kcode);
			;
			break;
		case menu:
			menu.keypressed(kcode);
			;
			break;
		case over:
			over.keypressed(kcode);
			;
			break;
		}

	}

	public void update() {
		switch (state) {
		case notstarted:
			notstarted.update();
			break;
		case running:
			running.update();
			break;
		case menu:
			menu.update();
			break;
		case over:
			over.update();
			break;
		}
	}

	public void Render(Graphics2D g2d) {
		switch (state) {
		case notstarted:
			notstarted.draw(g2d);
			break;
		case running:
			running.draw(g2d);
			break;
		case menu:
			running.draw(g2d);
			menu.draw(g2d);
			break;
		case over:
			over.draw(g2d);
			break;
		}
	}
}
