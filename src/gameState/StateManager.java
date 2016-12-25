package gameState;

import java.awt.Graphics2D;

public class StateManager {
	public enum State {
		PRE_START, RUNNING, MENU, OVER
	}

	State state;
	
	private Menu menu;
	private PreStart prestart;
	private GameOver over;
	Running running;

	public StateManager() {
		menu = new Menu(this);
		prestart = new PreStart(this);
		over = new GameOver(this);
		running = new Running(this);
		state = State.PRE_START;
	}

	public void keypressed(int kcode) {
		switch (state) {
		case PRE_START:
			prestart.keypressed(kcode);
			;
			break;
		case RUNNING:
			running.keypressed(kcode);
			;
			break;
		case MENU:
			menu.keypressed(kcode);
			;
			break;
		case OVER:
			over.keypressed(kcode);
			;
			break;
		}

	}

	public void update() {
		switch (state) {
		case PRE_START:
			prestart.update();
			break;
		case RUNNING:
			running.update();
			break;
		case MENU:
			menu.update();
			break;
		case OVER:
			over.update();
			break;
		}
	}

	public void Render(Graphics2D g2d) {
		switch (state) {
		case PRE_START:
			prestart.draw(g2d);
			break;
		case RUNNING:
			running.draw(g2d);
			break;
		case MENU:
			running.draw(g2d);
			menu.draw(g2d);
			break;
		case OVER:
			over.draw(g2d);
			break;
		}
	}
}
