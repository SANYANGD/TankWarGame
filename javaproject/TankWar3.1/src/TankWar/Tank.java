package TankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

/**
 * 主战坦克类
 * @author qbg
 *
 */
public class Tank {
	/**
	 * 主战坦克初始宽度，默认为50
	 */
	public static  int TANK_WIDTH=50;
	/**
	 * 主战坦克初始高度，默认为50
	 */
	public static final int TANK_HEIGHT=50;
	private static Random r=new Random();
	private  int XSPEED=3;
	private  int YSPEED=3;
	private int x,y;	//Tank起始位置
	private int oldX,oldY;
	//Tank移动方向
	private boolean bL=false,bU=false,bR=false,bD=false;
	private Direction dir=Direction.STOP;
	private TankBarrel bar=new TankBarrel();
	private boolean live=true;
	private TankClient tc;
	private int superFireCount=5;
	private int superShieldCount=3;
	private int lifeValue=100;
	private int lifeCount=3;
	private BloodBar bb=null;
	private long score=0;
	private int oldXsP=0;
	private int oldYsP=0;
	private boolean runabled=false;
	
	/**
	 * 主战坦克构造函数1
	 * @param x  初始化X位置
	 * @param y  初始化Y位置
	 */
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
		this.oldX=x;
		this.oldY=y;
	}
	/**
	 * 主战坦克构造函数2
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param tc 大管家：TankClient
	 */
	public Tank(int x,int y,TankClient tc){
		this(x,y);
		this.tc=tc;
		bb=new BloodBar(x,y-10,Tank.TANK_WIDTH,10);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/**
	 * 重置主战坦克速度
	 * @param xS X方向速度
	 * @param yS Y方向速度
	 */
	public void setSpeed(int xS,int yS){
		this.XSPEED=xS;
		this.YSPEED=yS;
	}
	/**
	 * 勾画主战坦克
	 * @param g 画笔
	 */
	public void draw(Graphics g){
		if(!live) {
			
			return;
		}
		//画炮筒
		bar.x=this.x;
		bar.y=this.y;
		move();
		bar.draw(g);
		//画血块
		bb.setbWidth(Tank.TANK_WIDTH*lifeValue/100);
		bb.setX(x);
		bb.setY(y-10);
		bb.draw(g);
	}
	/**
	 * 键盘按下事件处理函数
	 * @param e  键盘事件
	 */
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_RIGHT: 
			bR=true;
			break;
		case KeyEvent.VK_LEFT:
			bL=true;
			break;
		case KeyEvent.VK_UP:
			bU=true;
			break;
		case KeyEvent.VK_DOWN:
			bD=true;
			break;
		}
		locateDirection();
	}
	/**
	 * 键盘释放事件处理函数
	 * @param e 键盘事件
	 */
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_CONTROL:
			fire();
			break;
		case KeyEvent.VK_RIGHT: 
			bR=false;
			break;
		case KeyEvent.VK_LEFT:
			bL=false;
			break;
		case KeyEvent.VK_UP:
			bU=false;
			break;
		case KeyEvent.VK_DOWN:
			bD=false;
			break; 
		case KeyEvent.VK_S:
			this.superFire();
			break;
		case KeyEvent.VK_W:
			this.superShield();
			break;
		case KeyEvent.VK_ENTER:
			this.lifeValue=100;
			this.live=true;
			this.setLifeCount(3);
			break;
		case KeyEvent.VK_1:
			RobotTank.InitRbTanks(this.tc,15);
			break;
		case KeyEvent.VK_2:
			RobotTank.InitRbTanks(this.tc,20);
			break;
		case KeyEvent.VK_3:
			RobotTank.InitRbTanks(this.tc,30);
			break;
		case KeyEvent.VK_SPACE:
		 if(!runabled){
			oldXsP=this.getXSPEED();
			oldYsP=this.getYSPEED();
			this.setSpeed(5, 5);
			runabled=true;
		 }else{
			this.setSpeed(oldXsP, oldYsP);
			runabled=false;
		 }
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
		locateDirection();
	}
	/*
	 * 改变坦克方向
	 */
	private void locateDirection(){
		if(bL && !bU && !bR && !bD){
			dir=Direction.L;
		}else if(bL && bU && !bR && !bD){
			dir=Direction.LU;
		}else if(!bL && bU && !bR && !bD){
			dir=Direction.U;
		}else if(!bL && bU && bR && !bD){
			dir=Direction.RU;
		}else if(!bL && !bU && bR && !bD){
			dir=Direction.R;
		}else if(!bL && !bU && bR && bD){
			dir=Direction.RD;
		}else if(!bL && !bU && !bR && bD){
			dir=Direction.D;
		}else if(bL && !bU && !bR && bD){
			dir=Direction.LD;
		}else {
			dir=Direction.STOP;
		}
	}
	/*
	 * 控制坦克移动
	 */
	private void move(){
		oldX=x;
		oldY=y;
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
		case STOP:
			break;
		}
		if(this.dir!=Direction.STOP){
			bar.brDir=this.dir;
		}
		if(x<0) x=0;
		if(y<30) y=30;
		if(x+Tank.TANK_WIDTH > TankClient.GAME_WIDTH) x=TankClient.GAME_WIDTH - Tank.TANK_WIDTH;
		if(y+Tank.TANK_HEIGHT > TankClient.GAME_HEIGHT) y=TankClient.GAME_HEIGHT - Tank.TANK_HEIGHT;
	}
	public void stay(){
		x=oldX;
		y=oldY;
	}
	/**
	 *主战坦克开火
	 * @return 主战坦克发射的炮弹
	 */
	public Missile fire(){
		if(!live){
			return null;
		}
		int x=this.x + TANK_WIDTH/2 - Missile.MWIDTH/2;
		int y=this.y + TANK_HEIGHT/2 - Missile.MHEIGHT/2;
		Missile m=new Missile(x,y,bar.brDir,this.tc);
		m.getmI().setTankType(true);
		tc.missiles.add(m);
		return m;
		
	}
	/**
	 * 主战坦克开火
	 * @param dir 开火方向
	 * @return 发射的炮弹
	 */
	public Missile fire(Direction dir){
		if(!live){
			return null;
		}
		int x=this.x + TANK_WIDTH/2 - Missile.MWIDTH/2;
		int y=this.y + TANK_HEIGHT/2 - Missile.MHEIGHT/2;
		Missile m=new Missile(x,y,dir,this.tc);
		m.getmI().setTankType(true);
		tc.missiles.add(m);
		return m;
		
	}
	/**
	 * 主战坦克发射超级炮弹
	 * --可以向八个方向发射
	 */
	public void superFire(){
		if(superFireCount>0){
			Direction[] dir=Direction.values();
			for(int i=0;i<dir.length-1;i++){
				this.fire(dir[i]);
			}
			superFireCount--;
		}
	}
	public void superShield(){
		int sWH=r.nextInt(Tank.TANK_WIDTH+100)+Tank.TANK_WIDTH+10;
		int xp=x+Tank.TANK_WIDTH/2-sWH/2;
		int yp=y+Tank.TANK_HEIGHT/2-sWH/2;
		if(superShieldCount>0){
			tc.superShields.add(new SuperShield(xp,yp,sWH,sWH));
			superShieldCount--;
		}
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
	/**
	 * 获得碰撞检测矩形区域
	 * @return 碰撞检测矩形区域
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
	}
	/**
	 * 检查主战坦克是否与墙碰撞
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
	 * 检查主战坦克是否与机器坦克碰撞
	 * @param rbts 机器坦克
	 * @return 碰撞返回TRUE，否则返回FALSE
	 */
	public boolean collidesWithRbTank(List<RobotTank> rbts){
		if(rbts!=null){
			for(int i=0;i<rbts.size();i++){
				RobotTank rb=rbts.get(i);
				if(this.live && rb.isLive() && this.getRect().intersects(rb.getRect())){
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
	 * 检查主战坦克是否吃到血块
	 * @param bloods 血块-可以加血
	 * @return 吃到返回TRUE,否则返回
	 */
	public boolean eatBloods(List<Blood> bloods){
		if(bloods!=null){
			for(int i=0;i<bloods.size();i++){
				Blood b=bloods.get(i);
				if(live && b.isLive() && this.getRect().intersects(b.getRect())){
					this.setLifeValue(100);
					this.setLifeCount(this.getLifeCount()+2);
					b.setLive(false);
					return true;
				}
			}
			return false;
		}
		return false;
	}
	/**
	 * 检查主战坦克是否吃到弹夹
	 * @param bullets 弹夹-可以加超级炮弹
	 * @return 吃到返回TRUE,否则返回
	 */
	public boolean eatBullets(List<Bullet> bullets){
		if(bullets!=null){
			for(int i=0;i<bullets.size();i++){
				Bullet b=bullets.get(i);
				if(live && b.isLive() && this.getRect().intersects(b.getRect())){
					this.superFireCount += 3;
					b.setLive(false);
					return true;
				}
			}
			return false;
		}
		return false;
	}
	public boolean eatShields(List<Shield> shields){
		if(shields!=null){
			for(int i=0;i<shields.size();i++){
				Shield s=shields.get(i);
				if(live && s.isLive() && this.getRect().intersects(s.getRect())){
					this.superShieldCount += 2;
					s.setLive(false);
					return true;
				}
			}
			return false;
		}
		return false;
	}
	public void setSuperFireCount(int superFireCount) {
		this.superFireCount = superFireCount;
	}
	public int getSuperFireCount() {
		return superFireCount;
	}
	public void setLifeValue(int lifeValue) {
		this.lifeValue = lifeValue;
	}
	public int getLifeValue() {
		return lifeValue;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public long getScore() {
		return score;
	}
	public int getXSPEED() {
		return XSPEED;
	}
	public int getYSPEED() {
		return YSPEED;
	}
	public int getSuperShieldCount() {
		return superShieldCount;
	}
	public void setLifeCount(int lifeCount) {
		this.lifeCount = lifeCount;
	}
	public int getLifeCount() {
		return lifeCount;
	}
}
