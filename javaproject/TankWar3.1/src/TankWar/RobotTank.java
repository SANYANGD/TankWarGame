package TankWar;

import java.awt.*;

import TankWar.Direction;
import java.util.List;
import java.util.Random;

/**
 * 机器坦克类
 * @author qbg
 *
 */
public class RobotTank {
	/**
	 * 机器坦克初始长度,默认为30
	 */
	public static final int TANK_WIDTH=50;
	/**
	 * 机器坦克初始高度，默认为30
	 */
	public static final int TANK_HEIGHT=50;
	/**
	 * 机器坦克初始数量，默认为10
	 */
	public static final int RBTANK_COUNT=10;
	/**
	 * 机器坦克初始X方向速度，默认为3
	 */
	public static final int XSPEED=3;
	/**
	 * 机器坦克初始Y方向速度，默认为3
	 */
	public static final int YSPEED=3;
	private int x,y;	//Tank起始位置
	private int oldX,oldY;
	//Tank移动方向
	private Direction rbdir=Direction.STOP;
	private TankBarrel rbbar=new TankBarrel();
	private boolean live=true;
	private static TankClient tc;
	private static Random r=new Random();
	private int step=r.nextInt(12)+3;
	/**
	 * 机器坦克构造函数1
	 * @param x 初始X位置
	 * @param y 初始Y位置
	 */
	public RobotTank(int x,int y){
		this.x=x;
		this.y=y;
		this.oldX=x;
		this.oldY=y;
	}
	/**
	 * 机器坦克构造函数2
	 * @param x 初始X位置
	 * @param y 初始Y位置
	 * @param tc 大管家--TankClient
	 */
	public RobotTank(int x,int y,TankClient tc){
		this(x,y);
		RobotTank.tc=tc;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/**
	 * 机器坦克绘画函数
	 * @param g 画笔
	 */
	public void draw(Graphics g){
		if(!live) {
			tc.RbTanks.remove(this);
			return;
		}
		rbbar.x=this.x;
		rbbar.y=this.y;
		rbbar.draw(g);
		move();
	}
	
	private void move(){
		oldX=x;
		oldY=y;
		switch (rbdir){
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
		case STOP:
			break;
		}
		if(this.rbdir!=Direction.STOP){
			rbbar.brDir=this.rbdir;
		}
		if(x<0) x=0;
		if(y<30) y=30;
		if(x+RobotTank.TANK_WIDTH > TankClient.GAME_WIDTH) x=TankClient.GAME_WIDTH - RobotTank.TANK_WIDTH;
		if(y+RobotTank.TANK_HEIGHT > TankClient.GAME_HEIGHT) y=TankClient.GAME_HEIGHT - RobotTank.TANK_HEIGHT;
		
		Direction[] dirs=Direction.values();
		if(step<=0){
			step=r.nextInt(12)+3;
			int rn=r.nextInt(dirs.length);
			rbdir=dirs[rn];
		}
		step--;
		if(r.nextInt(40)>36) this.fire();
		
	}
	public void stay(){
		x=oldX;
		y=oldY;
	}
	/**
	 * 机器坦克开火函数
	 * @return 反射的炮弹
	 */
	public Missile fire(){
		if(!live) return null;
		int x=this.x + TANK_WIDTH/2 - Missile.MWIDTH/2;
		int y=this.y + TANK_HEIGHT/2 - Missile.MHEIGHT/2;
		Missile m=new Missile(x,y,rbbar.brDir,RobotTank.tc);
		m.getmI().setTankType(false);
		tc.rbmissiles.add(m);
		return m;
	}
	/**
	 * 碰撞检查矩形区域
	 * @return 矩形区域
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,RobotTank.TANK_WIDTH,RobotTank.TANK_HEIGHT);
	}
	/**
	 * 初始化机器坦克群--方法分配不合理，不建议使用
	 * @param tc 大管家--TankClient
	 */
	public static void InitRbTanks(TankClient tc){
		int xPos=r.nextInt(TankClient.GAME_WIDTH);
		int yPos=r.nextInt(TankClient.GAME_HEIGHT);
		RobotTank.tc=tc;
		for(int i=0;i<RobotTank.RBTANK_COUNT;i++){
			if(xPos<0 || xPos+RobotTank.TANK_WIDTH>TankClient.GAME_WIDTH || yPos<0 || yPos+RobotTank.TANK_HEIGHT>TankClient.GAME_HEIGHT){
				continue;
			}
			RobotTank rb=new RobotTank(xPos,yPos);
			if(rb.collidesWithTank(tc.myTank)){
				continue;
			}
			tc.RbTanks.add(rb);
			xPos=r.nextInt(TankClient.GAME_WIDTH);
			yPos=r.nextInt(TankClient.GAME_HEIGHT);
		}
	}
	/**
	 * 初始化机器坦克群--方法分配不合理,不建议使用
	 * @param tc 大管家--TankClient
	 * @param count 机器坦克数量
	 */
	public static void InitRbTanks(TankClient tc,int count){
		int xPos=r.nextInt(TankClient.GAME_WIDTH);
		int yPos=r.nextInt(TankClient.GAME_HEIGHT);
		RobotTank.tc=tc;
		for(int i=0;i<count;i++){
			if(xPos<0 || xPos+RobotTank.TANK_WIDTH>TankClient.GAME_WIDTH || yPos<0 || yPos+RobotTank.TANK_HEIGHT>TankClient.GAME_HEIGHT){
				continue;
			}
			RobotTank rb=new RobotTank(xPos,yPos);
			if(rb.collidesWithTank(tc.myTank)){
				continue;
			}
			tc.RbTanks.add(rb);
			xPos=r.nextInt(TankClient.GAME_WIDTH);
			yPos=r.nextInt(TankClient.GAME_HEIGHT);
		}
	}
	/**
	 * 检测机器坦克是否与墙碰撞
	 * @param walls 墙
	 * @return 碰撞返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithWall(List<Wall> walls){
		if(walls!=null){
			for(int i=0;i<walls.size();i++){
				Wall w=walls.get(i);
				if(this.live && this.getRect().intersects(w.getRect())){
					stay();
					return true;
				}
			}
			return false;
		}
		return false;
	}
	/**
	 * 检测机器坦克之间是否碰撞
	 * @param rbts 机器坦克群
	 * @return 碰撞返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithRbTank(List<RobotTank> rbts){
		if(rbts!=null){
			for(int i=0;i<rbts.size();i++){
				RobotTank rb=rbts.get(i);
				if(this != rb && this.live && rb.isLive() && this.getRect().intersects(rb.getRect())){
					this.stay();
					rb.stay();
					return true;
				}
			}
			return false;
		}
		return false;
	}
	/**
	 * 检测机器坦克是否与主战坦克碰撞
	 * @param t 主战坦克
	 * @return 碰撞返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithTank(Tank t){
		if(this.live && t.isLive() && this.getRect().intersects(t.getRect())){
			this.stay();
			t.stay();
			return true;
		}
		return false;
	}

}
