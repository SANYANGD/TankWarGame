package TankWar;

import java.awt.*;

/**
 * 地图中墙类
 * @author qbg
 *
 */
public class Wall {
	private static final Color WALLCOLOR = Color.DARK_GRAY;
	private int x,y;
	private int wallWidth,wallHeight;
	/**
	 * 墙构造函数
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param width 初始化宽度
	 * @param height 初始化高度
	 * @param tc 大管家-TankClient
	 */
	public Wall(int x,int y,int width,int height,TankClient tc){
		this.x=x;
		this.y=y;
		this.wallWidth=width;
		this.wallHeight=height;
	}
	/**
	 * 墙绘画函数
	 * @param g 画笔
	 */
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(WALLCOLOR);
		g.fillRect(x, y, wallWidth, wallHeight);
		g.setColor(c);
	}
	/**
	 * 墙碰撞检测矩形区域
	 * @return 矩形区域
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,wallWidth,wallHeight);
	}
}
