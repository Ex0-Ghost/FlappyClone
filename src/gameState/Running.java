package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

import gameState.StateManager.State;
import mybird.*;

public class Running implements GameState {
	StateManager stateManager;
	public int score = 0;
	private static int cs = 0;// column set either 0 or 1 see spawncolumn()
								// method
	public static Bird bird = new Bird();
	public static Random rand = new Random();
	public static Background background = new Background();
	public static int ygap = 120;
	public static int xgap = 350;
	// ====creating columns=====//
	public static TopColumn column1 = new TopColumn(); // creating top column
	public static BottomColumn column2 = new BottomColumn(); // creating bottom
																// column
	public static TopColumn column3 = new TopColumn(); // creating top column
	public static BottomColumn column4 = new BottomColumn(); // creating bottom
																// column
	public static Obstacle columns[] = { column1, column2, column3, column4 }; // an
																				// array
																				// contains
																				// all
																				// columns

	public Running(StateManager statemanager) {
		// TODO Auto-generated constructor stub
		stateManager = statemanager;
		bird.x = Main.frame.getWidth() / 2 - 180;
		bird.y = Main.frame.getHeight() / 2 - 20;
	}

	public void jump() {
		if (Main.soundstate == "on") {
			Sound.playSound("jump.wav", 0);
		}
		bird.yv = -12;
		bird.theta = -1;
	}

	public void startgame() {
		stateManager.state = State.running;
		score = 0;
		bird.x = Main.frame.getWidth() / 2 - 180;
		bird.y = Main.frame.getHeight() / 2 - 20;
		bird.yv = 0;
		spawncolumn();// spawn columns
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		background.move();
		bird.update();
		for (int i = 0; i < 4; i = i + 2) { // add 1 score if we pass an column.
											// just checking one of two pipe
											// pairs no need for the other
			if (stateManager.state == State.running && bird.x > columns[i].x + 50 && !columns[i].passed) {
				score++;
				columns[i].passed = true;
			}
		}
		if (bird.y > 580 || bird.coll(column1) || bird.coll(column2) || bird.coll(column3) || bird.coll(column4)) {// if
																													// our
																													// bird
																													// collides
																													// with
																													// an
																													// column
			stateManager.state = State.over;
			for (int i = 0; i < 4; i++) {
				columns[i].reset();
			}
			cs = 0;// this is useful in spawning columns look at that method
		} else {
			// reseting columns if its off screen needs some work
			for (int i = 0; i < 3; i = i + 2) {
				if (columns[i].x < -52)
					spawncolumn();
			}

			if ((column1.x < 400) && (!column3.visible))
				spawncolumn(); // spawn the second set of colomun for the first
								// time

			if (bird.y + bird.yv < 0) { // if bird jump (y velocity) throws the
										// bird outside screen then fit the jump
										// to remaining to the top
				bird.yv = 0 - bird.y;
			}
			bird.y = bird.y + bird.yv; // y velocity
			bird.yv++; // gravity effect
			for (int i = 0; i < 4; i++) { // move the columns if exists(visible)
				if (columns[i].visible)
					columns[i].x = columns[i].x - 6;
			}

		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		// draw the game
		background.draw(g2d);
		bird.draw(g2d); // draw bird

		for (int i = 0; i < 4; i++) {// draw columns
			columns[i].draw(g2d);
		}
		g2d.setColor(Color.white);
		g2d.setFont(new Font("temp", 1, 20));
		g2d.drawString("Score: " + score, 20, 20);
		g2d.drawString("sound: " + Main.soundstate, 120, 20);
	}

	public void spawncolumn() {
		Obstacle current1 = column1, current2 = column2;
		if (cs == 1) {
			current1 = column3;
			current2 = column4;
			cs = -1;// at the end c will be 0 and firs column gonna spawn
		}
		current1.x = 800;
		current2.x = 800;
		current1.passed = false;
		current2.passed = false;
		current1.y = -current1.height + 120 + rand.nextInt(250);
		current2.y = current1.y + current1.height + ygap;
		current1.visible = true;
		current2.visible = true;
		cs++;
	}

	@Override
	public void keypressed(int kcode) {
		// TODO Auto-generated method stub
		switch (kcode) {
		case 32: // space key code
			jump();
			break;
		case 81: // Q key code
			Main.muteSwithch();
			break;
		case 27: // Esc key code
			stateManager.state = State.menu;
			break;
		}
	}
}