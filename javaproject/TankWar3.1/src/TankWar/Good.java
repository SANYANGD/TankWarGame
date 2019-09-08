package TankWar;

import java.awt.*;
import java.util.Random;
import TankWar.Direction;

/**
 * ���ָ�����ĸ���
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
		 * ��ܼ�-TankClient
		 */
		public static TankClient tc;
		/**
		 * �ƶ�����
		 */
		public int step=0;
		/**
		 * ��������ɶ���r
		 */
		public static Random r=new Random();
		public boolean isLive() {
			return live;
		}
		/**
		 * Good���캯��1
		 * @param x ��ʼ��Xλ��
		 * @param y ��ʼ��Yλ��
		 * @param dir ��ʼ������
		 */
		public Good(int x,int y,Direction dir){
			this.x=x;
			this.y=y;
			this.dir=dir;
		}
		/**
		 * Good���캯��2
		 * @param x ��ʼ��Xλ��
		 * @param y	��ʼ��Yλ��
		 * @param dir ��ʼ������
		 * @param tc ��ܼ�--TankClient
		 */
		public Good(int x,int y,Direction dir,TankClient tc){
			this(x,y,dir);
			Good.tc=tc;
		}
		/**
		 * Good�滭����
		 * @param g ����
		 */
		public void draw(Graphics g){}
		/**
		 * Good�ƶ�����
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
		 * Good��ײ����������
		 * @return ��������
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
