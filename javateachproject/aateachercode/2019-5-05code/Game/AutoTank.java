package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Random;

public class AutoTank extends Tank{
	IIntelligent goCategory=null;
	public AutoTank(){
		super(400,50,34,34);
		Random random = new Random();
		int direction = random.nextInt(3)+2;//up
		setDirection(direction);
	}
	public void setGoCategory(IIntelligent goCategory) {
		this.goCategory = goCategory;
	}
	public void draw(Graphics g){			
		ImgSource.getInstance().drawAutoTank(g,state,getX(),getY(),getDirection());
		
	}
	public void go(){
		setDirection(goCategory.getDirection(getX(), getY(), getDirection()));
		super.go();
		goForward();
	}
	public Bullet fire() {
		Bullet bullet = null;
		if(fireCoolTime >= FIRCOOLTIME) {
			Random random = new Random();
			int i = random.nextInt(5);//up
			if(i == 1) {
				bullet = new Bullet();
				bullet.setX(getX());
				bullet.setY(getY());
				bullet.setDirection(getDirection());
					
			}
			fireCoolTime = 0;
		}
		return bullet;
	}
	
}
