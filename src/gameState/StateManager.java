package gameState;

import java.awt.Graphics2D;

public class StateManager {
	public enum State {//this enum is just used to setState or getState
		PRE_START, RUNNING, MENU, OVER
	}

	private GameState currentState;
	private Menu menu;
	private PreStart prestart;
	private GameOver over;
	private Running running;

	public StateManager() {
		menu = new Menu(this);
		prestart = new PreStart(this);
		over = new GameOver(this);
		running = new Running(this);
		currentState = prestart;
	}
	
	public void setState(State state){
		switch (state) {
		case PRE_START:
			currentState = prestart;
			break;
		case RUNNING:
			currentState = running;
			break;
		case MENU:
			currentState = menu;
			break;
		case OVER:
			currentState = over;
			break;
	}
		
}
	
	public GameState getState(State state){//temporary fix for states to see each other
		switch (state) {
		case PRE_START:
			return prestart;
			
		case RUNNING:
			return running;
		case MENU:
			return menu;
		case OVER:
			return over;
		default:
			return currentState;
	}
	}
	public void keypressed(int kcode) {
		currentState.keypressed(kcode);
	}

	public void update() {
		currentState.update();
	}

	public void Render(Graphics2D g2d) {
		if(currentState== menu){//if we're at menu draw the game behind it 
			running.draw(g2d);
		}
		currentState.draw(g2d);
		}
	}
