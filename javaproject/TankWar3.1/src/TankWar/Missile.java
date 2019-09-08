package TankWar;

import TankWar.Direction;

import java.awt.*;
import java.util.List;

/**
 * 坦克炮弹类
 * @author qbg
 *
 */
public class Missile {
	/**
	 * 炮弹初始X方向速度，默认为6
	 */
	public static final int XSPEED=6;
	/**
	 * 炮弹初始Y方向速度，默认为6
	 */
	public static final int YSPEED=6;
	/**
	 * 炮弹初始宽度，默认为8
	 */
	public static final int MWIDTH=12;
	/**
	 * 炮弹初始高度，默认为8
	 */
	public static final int MHEIGHT=12;
	private int x,y;
	private Direction dir;
	private TankClient tc;
	private boolean live=true;
	private MissileImages mI=new MissileImages();
	
	public boolean isLive() {
		return live;
	}
	/**
	 * 炮弹初始构造函数1
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param dir 初始化飞行方向
	 */
	public Missile(int x,int y,Direction dir){
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	/**
	 * 炮弹初始构造函数2
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param dir 初始化飞行方向
	 * @param tc 大管家--TankClient
	 */
	public Missile(int x,int y,Direction dir,TankClient tc){
		this(x,y,dir);
		this.tc=tc;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/**
	 *炮弹勾画函数
	 * @param g Graphics
	 */
	public void draw(Graphics g){
		if(!live){
			tc.missiles.remove(this);
			tc.rbmissiles.remove(this);
			return;
		}
		mI.setX(x);
		mI.setY(y);
		mI.setMiDir(dir);
		mI.draw(g);
		move();
	}
	private void move() {
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
	}
	/**
	 * 碰撞检测矩形区域
	 * @return 矩形区域
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,Missile.MWIDTH,Missile.MHEIGHT);
	}
	/**
	 * 炮弹攻击主战坦克
	 * @param t 被攻击的主战坦克
	 * @return 击中返回TRUE，否则返回
	 */
	public boolean hitTank(Tank t){
		if(live && this.getRect().intersects(t.getRect()) && t.isLive()){
			t.setLifeValue(t.getLifeValue()-20);
			if(t.getLifeValue()<=0){
				t.setLive(false);
				Explode e=new Explode(this.x,this.y,this.tc);
				tc.explodes.add(e);
			}
			this.live=false;
			//播放子弹声音
			GameSound.play_bomb();
			return true;
		}
		return false;
	}
	/**
	 * 炮弹攻击机器坦克
	 * @param rbt 被攻击的机器坦克
	 * @return 击中返回TRUE，否则返回
	 */
	public boolean hitTank(RobotTank rbt){
		if(live && this.getRect().intersects(rbt.getRect()) && rbt.isLive()){
			rbt.setLive(false);
			this.live=false;
			Explode e=new Explode(rbt.getX(),rbt.getY(),this.tc);
			tc.explodes.add(e);
			//播放子弹声音
			GameSound.play_bomb();
			return true;
		}
		return false;
	}
	/**
	 * 跑弹攻击机器坦克群
	 * @param rbts 被攻击的机器坦克群
	 * @return 击中返回TRUE,否则返回
	 */
	public boolean hitTank(List<RobotTank> rbts){
		if(rbts!=null){
			for(int i=0;i<rbts.size();i++){
				if(hitTank(rbts.get(i))){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 检查炮弹是否碰撞墙
	 * @param walls 墙
	 * @return 撞中返回TRUE,否则返回
	 */
	public boolean collidesWithWall(List<Wall> walls){
		if(walls!=null){
			for(int i=0;i<walls.size();i++){
				Wall w=walls.get(i);
				if(this.live && this.getRect().intersects(w.getRect())){
					live=false;
					return true;
				}
			}
		}
		return false;
	}
	public boolean collidesWithShield(List<SuperShield> superShields){
		if(superShields!=null){
			for(int i=0;i<superShields.size();i++){
				SuperShield ss=superShields.get(i);
				if(live && this.getRect().intersects(ss.getRect())){
					this.live=false;
					return true;
				}
			}
	        return false;
		}
		return false;
	}
	public MissileImages getmI() {
		return mI;
	}
}
