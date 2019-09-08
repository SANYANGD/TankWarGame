import java.awt.Graphics;

public class Tank {
	private int x,y;
	private int width,height;
	private int direction;
	public Tank(){
		x = 100;
		y = 100;
		width = 28;
		height = 28;
		direction = 4;//up
	}
	private void drawUp(Graphics g){
		g.drawRect(x - width/2,y - height/2,4,height);
		g.drawRect(x + width/2-4,y - height/2,4,height);
		g.drawRect(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawOval(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawRect(x -1,y - height/2,3,height/2);
	}
	private void drawRight(Graphics g){
		g.drawRect(x - width/2,y - height/2,width,4);
		g.drawRect(x - width/2,y + height/2-4,width,4);
		g.drawRect(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawOval(x - width/2+4,y - height/2+4,width-8,height-8);
		
		g.drawRect(x,y-1,width/2,3);
	}
	private void drawDown(Graphics g){
		g.drawRect(x - width/2,y - height/2,4,height);
		g.drawRect(x + width/2-4,y - height/2,4,height);
		g.drawRect(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawOval(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawRect(x-1,y,3,height/2);
	}
	private void drawLeft(Graphics g){
		g.drawRect(x - width/2,y - height/2,width,4);
		g.drawRect(x - width/2,y + height/2-4,width,4);
		g.drawRect(x - width/2+4,y - height/2+4,width-8,height-8);
		g.drawOval(x - width/2+4,y - height/2+4,width-8,height-8);
		
		g.drawRect(x-width/2,y-1,width/2,3);
	}
	public void draw(Graphics g){
		switch(direction){
		case 1:
			drawUp(g);break;
		case 2:
			drawRight(g);break;
		case 3:
			drawDown(g);break;
		case 4:
			drawLeft(g);break;
		}
	}
	public void setDirection(int dir){
		if(dir>0 && dir<=4){
			direction = dir;
		}
	}
	public void go(){
		switch(direction){
		case 1:
			y = y-1;break;
		case 2:
			x = x+1;break;
		case 3:
			y = y+1;break;
		case 4:
			x = x-1;break;
		}
	}

}
