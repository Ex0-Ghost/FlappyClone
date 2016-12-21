package mybird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameState.StateManager;

public class Tasker implements KeyListener {
	StateManager stateManager = Main.stateManager;

	public void updateandrender() {
		stateManager.update();
		Main.panel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		stateManager.keypressed(e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
