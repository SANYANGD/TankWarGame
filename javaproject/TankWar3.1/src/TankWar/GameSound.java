package TankWar;

import java.applet.Applet;
import java.applet.AudioClip;

public class GameSound extends Applet{
	
	private static final long serialVersionUID = 1L;

	private static AudioClip bomb;//±¬Õ¨Éù
	private static AudioClip tank;//Ì¹¿ËÂÄ´øÉù
	
	public static void play_bomb(){
		bomb=Applet.newAudioClip(GameSound.class.getClassLoader().getResource("audio/bomb.wav"));
		bomb.play();
	}
	public static void play_tank(){
		tank=Applet.newAudioClip(GameSound.class.getClassLoader().getResource("audio/tank.wav"));
		tank.play();
	}
	public static boolean stop_bomb(){
		if(bomb!=null){
			bomb.stop();
			return true;
		}
		return false;
	}
	public static boolean stop_tank(){
		if(tank!=null){
			tank.stop();
			return true;
		}
		return false;
	}
	public static void loop_tank(){
		tank=Applet.newAudioClip(GameSound.class.getClassLoader().getResource("audio/tank.wav"));
		tank.loop();
	}
	public static boolean loop_stop(){
		if(tank!=null){
			tank.stop();
			return true;
		}
		return false;
		
	}
}
