import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import java.awt.Point;
public abstract class Tank {
	private int x,y;
	private int v;
	private int width,height;
	private int direction;//1up
	protected int state = 0;
	
	public Tank(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		v = 3;			
	}
	public void setVelocity(int v){
		this.v = v;
	}
	public Rectangle getBounds(){
		return new Rectangle(x-width/2,y-height/2,width,height);
	}
	
	
	public void draw(Graphics g){
			
	}
	
	
	public void setDirection(int dir){
		if(dir>0 && dir<=4){
			direction = dir;
		}
		
	}
	public int getDirection(){
		return direction;
	}
	public void go(){
		
	}
	public Bullet fire(){
		Bullet bullet = new Bullet();
		bullet.setX(x);
		bullet.setY(y);
		bullet.setDirection(direction);
		return bullet;
	}
	public Point getNextPositon(){
		Point p = new Point();		
		p.x = x;
		p.y = y;
		switch(direction){
		case 1:
			p.y = y-v;break;
		case 2:
			p.x = x+v;break;
		case 3:
			p.y = y+v;break;
		case 4:
			p.x = x-v;break;
		}
		return p;
	}
	public void goForward(){
		if(state == 0){
			state = 1;
		}else{
			state = 0;
		}	
		switch(direction){
		case 1:
			y = y-v;break;
		case 2:
			x = x+v;break;
		case 3:
			y = y+v;break;
		case 4:
			x = x-v;break;
		}
	}
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	
}
