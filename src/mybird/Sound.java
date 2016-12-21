package mybird;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static ArrayList<Clip> clips = new ArrayList<Clip>();

	public static synchronized void playSound(final String url, int count) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Main.class.getResourceAsStream("/mybird/resources/sounds/" + url));
			clip.open(inputStream);
			clip.loop(count);
			clips.add(clip);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
