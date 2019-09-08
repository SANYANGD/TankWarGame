import java.awt.Graphics;

public class Bullet extends Tank{
	public Bullet(){
		super(-100,-100,34,34);
		setVelocity(10);
	}
	public void draw(Graphics g){
		
		ImgSource.getInstance().drawBullet(g,0,getX(),getY(),getDirection());
	}
	public void go(){
		goForward();
	}
	

}
