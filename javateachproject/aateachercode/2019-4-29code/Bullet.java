import java.awt.Graphics;

import MapEditor.ImgSource;

public class Bullet extends Tank{
	public Bullet(){
		super(-100,-100,17,17);
		setVelocity(10);
	}
	public void draw(Graphics g){
		
		ImgSource.getInstance().drawBullet(g,0,getX(),getY(),getDirection());
	}
	public void go(){
		goForward();
	}
	

}
