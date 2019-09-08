package TankWar;

import TankWar.Direction;

import java.awt.*;
import java.util.List;

/**
 * ̹���ڵ���
 * @author qbg
 *
 */
public class Missile {
	/**
	 * �ڵ���ʼX�����ٶȣ�Ĭ��Ϊ6
	 */
	public static final int XSPEED=6;
	/**
	 * �ڵ���ʼY�����ٶȣ�Ĭ��Ϊ6
	 */
	public static final int YSPEED=6;
	/**
	 * �ڵ���ʼ��ȣ�Ĭ��Ϊ8
	 */
	public static final int MWIDTH=12;
	/**
	 * �ڵ���ʼ�߶ȣ�Ĭ��Ϊ8
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
	 * �ڵ���ʼ���캯��1
	 * @param x ��ʼ��Xλ��
	 * @param y ��ʼ��Yλ��
	 * @param dir ��ʼ�����з���
	 */
	public Missile(int x,int y,Direction dir){
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	/**
	 * �ڵ���ʼ���캯��2
	 * @param x ��ʼ��Xλ��
	 * @param y ��ʼ��Yλ��
	 * @param dir ��ʼ�����з���
	 * @param tc ��ܼ�--TankClient
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
	 *�ڵ���������
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
	 * ��ײ����������
	 * @return ��������
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,Missile.MWIDTH,Missile.MHEIGHT);
	}
	/**
	 * �ڵ�������ս̹��
	 * @param t ����������ս̹��
	 * @return ���з���TRUE�����򷵻�
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
			//�����ӵ�����
			GameSound.play_bomb();
			return true;
		}
		return false;
	}
	/**
	 * �ڵ���������̹��
	 * @param rbt �������Ļ���̹��
	 * @return ���з���TRUE�����򷵻�
	 */
	public boolean hitTank(RobotTank rbt){
		if(live && this.getRect().intersects(rbt.getRect()) && rbt.isLive()){
			rbt.setLive(false);
			this.live=false;
			Explode e=new Explode(rbt.getX(),rbt.getY(),this.tc);
			tc.explodes.add(e);
			//�����ӵ�����
			GameSound.play_bomb();
			return true;
		}
		return false;
	}
	/**
	 * �ܵ���������̹��Ⱥ
	 * @param rbts �������Ļ���̹��Ⱥ
	 * @return ���з���TRUE,���򷵻�
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
	 * ����ڵ��Ƿ���ײǽ
	 * @param walls ǽ
	 * @return ײ�з���TRUE,���򷵻�
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
