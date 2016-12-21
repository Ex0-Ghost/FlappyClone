package mybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameState.StateManager;

public class Main {
	public static StateManager stateManager;

	public static String soundstate = "on";

	// ====================//
	public static Tasker Tasker; // receives key press and action by timer
	public static JFrame frame = new JFrame(); // frame(window) for our game
	public static Thread gloop; // game loop
	public static JPanel panel = new JPanel() { // panel is the canvas we are
												// drawing on

		/**
			 * 
			 */
		private static final long serialVersionUID = -8688728818864932054L;

		protected void paintComponent(Graphics g) { // overriding default
													// component paint method to
													// paint the game
			Graphics2D g2d = (Graphics2D) g; // we cast it to graphics2d because
												// it has more feature
			stateManager.Render(g2d);
		};
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// initializing window
		frame.setTitle("flappy");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		stateManager = new StateManager();
		Tasker = new Tasker();
		frame.setContentPane(panel);
		frame.addKeyListener(Tasker); // tasker receives keys pressed
		frame.setResizable(false);
		gloop = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				/*
				 * some code to test game performance long count=0;
				 * 
				 * long biggest =0; long newbiggest =0;
				 */
				while (true) {
					/*
					 * some code to test game performance long el; count++;
					 * System.out.println("start time: " + System.nanoTime() +
					 * " or: " + System.currentTimeMillis()); long st =
					 * System.nanoTime();
					 */
					Tasker.updateandrender();
					/*
					 * some code to test game performance
					 * System.out.println("end time:   " + System.nanoTime() +
					 * " or: " + System.currentTimeMillis()); System.out.
					 * println("elapsed update and render time in nanos: " +
					 * (System.nanoTime()-st)); el=(System.nanoTime()-st);
					 * biggest = Math.max(biggest, el);
					 * 
					 * if(count>3000){ newbiggest = Math.max(newbiggest, el); }
					 * System.out.println("max: " + biggest +
					 * "new max after 50 seconds" + newbiggest);
					 */
					try {
						sleep(22);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
					}
				}
			}
		};
		gloop.start();
		Sound.playSound("bg.wav", 99); // play and loop background music for 99
										// times just in case our player has
										// nothing important to do

		// experimental reading file start:
		File file = new File("C:/save");
		FileReader filereader;
		try {
			filereader = new FileReader(file);
			char num[] = new char[20];
			filereader.read(num);
			System.out.println(num);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// end:
	}

	public static void muteSwithch() {
		if (soundstate == "on") {
			soundstate = "off";
			for (int i = 0; i <= Sound.clips.size(); i++) {
				try {
					Sound.clips.get(i).stop();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		} else {
			soundstate = "on";
			for (int i = 0; i <= Sound.clips.size(); i++) {
				try {
					Sound.clips.get(i).start();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}
	}

}
