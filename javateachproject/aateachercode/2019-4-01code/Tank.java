import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
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
		v = 1;			
	}
	public void setVelocity(int v){
		this.v = v;
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
	public void goForward(){
		if(state == 0){
			state = 1;
		}else{
			state = 0;
		}
		int ax,ay;
		ax = x;
		ay = y;
		switch(direction){
		case 1:
			ay = ay-v;break;
		case 2:
			ax = ax+v;break;
		case 3:
			ay = ay+v;break;
		case 4:
			ax = ax-v;break;
		}
		
		if(ax>width/2 && ax<800-width/2&&ay>height/2 && ay<600-height/2){
			
			x = ax;
			y = ay;
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
