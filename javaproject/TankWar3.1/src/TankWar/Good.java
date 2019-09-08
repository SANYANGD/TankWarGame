package TankWar;

import java.awt.*;
import java.util.Random;
import TankWar.Direction;

/**
 * 各种附加物的父类
 * @author qbg
 *
 */
public class Good { 
		private   int XSPEED=6;
		private   int YSPEED=6;
		private   int GWIDTH=12;
		private   int GHEIGHT=12;
		private   Color color=Color.BLACK;
		private int x,y;
		private boolean live=true;
		private  Direction dir;
		/**
		 * 大管家-TankClient
		 */
		public static TankClient tc;
		/**
		 * 移动步长
		 */
		public int step=0;
		/**
		 * 随机数生成对象r
		 */
		public static Random r=new Random();
		public boolean isLive() {
			return live;
		}
		/**
		 * Good构造函数1
		 * @param x 初始化X位置
		 * @param y 初始化Y位置
		 * @param dir 初始化方向
		 */
		public Good(int x,int y,Direction dir){
			this.x=x;
			this.y=y;
			this.dir=dir;
		}
		/**
		 * Good构造函数2
		 * @param x 初始化X位置
		 * @param y	初始化Y位置
		 * @param dir 初始化方向
		 * @param tc 大管家--TankClient
		 */
		public Good(int x,int y,Direction dir,TankClient tc){
			this(x,y,dir);
			Good.tc=tc;
		}
		/**
		 * Good绘画方法
		 * @param g 画笔
		 */
		public void draw(Graphics g){}
		/**
		 * Good移动控制
		 */
		public void move() {
			switch (dir){
			case L:
				x -= XSPEED;
				break;
			case LU:
				x -= XSPEED;
				y -= YSPEED;
				break;
			case U:
				 y -= YSPEED;
				break;
			case RU:
				x += XSPEED;
				y -= YSPEED;
				break;
			case R:
				x += XSPEED;
				break;
			case RD:
				x += XSPEED;
				y += YSPEED;
				break;
			case D:
				y += YSPEED;
				break;
			case LD:
				x -= XSPEED;
				y += YSPEED;
				break;
			}
			if(x<0 || y<0 || x>TankClient.GAME_WIDTH || y>TankClient.GAME_HEIGHT){
				live=false;
			}
			Direction[] dirs=Direction.values();
			if(step<=0){
				step=r.nextInt(15)+3;
				int rn=r.nextInt(dirs.length);
				dir=dirs[rn];
			}
			step--;
		}
		/**
		 * Good碰撞检测矩形区域
		 * @return 矩形区域
		 */
		public Rectangle getRect(){
			return new Rectangle(x,y,GWIDTH,GHEIGHT);
		}
		
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void setDir(Direction dir) {
			this.dir = dir;
		}
		public void setColor(Color c) {
			color = c;
		}
		public Color getColor() {
			return color;
		}
		public void setSpeed(int xS,int yS){
			XSPEED=xS;
			YSPEED=yS;
		}
		public void setShape(int xW,int yH){
			GWIDTH=xW;
			GHEIGHT=yH;
		}
		public int getGWIDTH() {
			return GWIDTH;
		}
		public int getGHEIGHT() {
			return GHEIGHT;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public void setLive(boolean live) {
			this.live = live;
		}
		
		
}
