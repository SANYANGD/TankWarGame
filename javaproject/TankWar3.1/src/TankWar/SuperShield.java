package TankWar;

import java.awt.*;

/**
 * ������������--��������װ���ĸ���
 * @author qbg
 *
 */
public class SuperShield {
	public static final Color shieldColor=Color.WHITE;
	private int x,y;
	private int sWidth,sHeight;
	
	public SuperShield(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.sWidth=width;
		this.sHeight=height;
	}
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(shieldColor);
		g.drawOval(x, y, sWidth, sHeight);
		g.setColor(c);
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,sWidth,sHeight);
	}
}
