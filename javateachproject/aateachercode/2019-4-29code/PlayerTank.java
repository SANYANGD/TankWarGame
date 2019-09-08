import java.awt.Graphics;
import java.util.Random;

import MapEditor.ImgSource;

public class PlayerTank extends Tank{
	private int fireCoolTime = 0;
	public PlayerTank(){
		super(100,100,34,34);	
		setDirection(1);
	}
	public void draw(Graphics g){
	
		ImgSource.getInstance().drawPlayerTank(g,state,getX(),getY(),getDirection());
	}
	public void go(){
		fireCoolTime++;
		goForward();
	}
	public Bullet fire(){
		Bullet b = null;
		if(fireCoolTime>10){			
			fireCoolTime = 0;
			b = super.fire();
		}
		return b;		
	}
}
