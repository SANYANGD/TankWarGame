package Game;

import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
public abstract class Tank {
	private int x,y;
	private int v;
	private int width,height;
	private int direction;//1up
	protected int state = 0;
	protected boolean alive = true; //是否存活
	protected int fireCoolTime = 0;
	protected final int FIRCOOLTIME=10;
	public Tank(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		v = 2;			
	}
	public void setVelocity(int v){
		this.v = v;
	}
	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public Rectangle getBounds() {
		// 创建 一个坐标在（x,y）位置，宽度为(width,height)的矩形边界对象并返回
		return new Rectangle(x,y,width,height);
	}
	/**
	 * 判断是否撞到其它坦克
	 * @param x -自身坦克的横坐标
	 * @param y -自身坦克的纵坐标
	 * @return 撞到任意坦克，则返回true
	 */
	public boolean hit(Rectangle r) {
		if(r == null) {//如果目标为空
			return false; //返回false,不发生碰撞
		}
		return getBounds().intersects(r); //返回两者的边界对象是否相交
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
		fireCoolTime++;		
	}
	public Bullet fire() {
		return null;
	}
	public Point getNextPosition() {
		Point point=new Point(x,y);
	
		switch(direction){
		case 1:
			point.y = y-v;break;
		case 2:
			point.x = x+v;break;
		case 3:
			point.y = y+v;break;
		case 4:
			point.x = x-v;break;
		}
		return point;
	}
	public void goForward(){
		if(state == 0){
			state = 1;
		}else{
			state = 0;
		}
		Point point;
		point = getNextPosition();
		x = point.x;
		y = point.y;
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
