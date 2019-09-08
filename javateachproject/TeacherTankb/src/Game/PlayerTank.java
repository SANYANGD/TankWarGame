package Game;

import java.awt.Graphics;
import java.util.Random;

public class PlayerTank extends Tank{
	public PlayerTank(){
		super(100,100,34,34);	
		setDirection(1);
	}
	public void draw(Graphics g){
	
		ImgSource.getInstance().drawPlayerTank(g,state,getX(),getY(),getDirection());
	}
	public void go(){
		super.go();
		goForward();
	}
	public Bullet fire() {
		Bullet bullet = null;
		if(fireCoolTime >= FIRCOOLTIME) {
			bullet = new Bullet();
			bullet.setX(getX());
			bullet.setY(getY());
			bullet.setDirection(getDirection());
			fireCoolTime = 0;			
		}
		return bullet;
	}
}
