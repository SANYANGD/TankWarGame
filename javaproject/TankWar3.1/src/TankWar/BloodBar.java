package TankWar;

import java.awt.*;

/**
 * 坦克生命血块显示类
 * @author qbg
 *
 */
public class BloodBar {
	/**
	 * 血块初始颜色，默认为橘黄色
	 */
	public Color bloodBarColor=Color.ORANGE;
	private int x,y,width,height,bWidth;
	
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setbWidth(int bWidth) {
		this.bWidth = bWidth;
	}
	/**
	 * 血块构造函数
	 * @param x 初始X位置
	 * @param y 初始Y位置
	 * @param width 初始宽度
	 * @param height 初始高度
	 */
	public BloodBar(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.bWidth=width;
		this.height=height;
	}
	/**
	 * 血块绘画函数
	 * @param g 画笔
	 */
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(bloodBarColor);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, bWidth, height);
		g.setColor(c);
	}
	
}
