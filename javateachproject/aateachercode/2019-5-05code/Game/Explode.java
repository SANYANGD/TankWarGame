package Game;

import java.awt.Graphics;

public class Explode {
	private int x;
	private int y;
	private int state=0;
	protected boolean alive = true; //ÊÇ·ñ´æ»î
	public Explode(int x,int y) {
		this.x = x;
		this.y = y;		
	}
	public boolean getAlive() {
		return alive;
	}
	public void draw(Graphics g){		
		ImgSource.getInstance().drawExplode(g,state,x,y);
		state++;
		if(state>=2) {
			alive = false;
		}
	}
}
