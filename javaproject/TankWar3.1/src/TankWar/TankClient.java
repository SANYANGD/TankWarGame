package TankWar;

import java.awt.*;
import java.awt.event.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 坦克大战主窗口类
 * @author qbg
 *
 */

public class TankClient extends Frame implements ActionListener{

	private static final long serialVersionUID = 1L;
	/**
	 * 游戏窗口初始宽度，默认为800
	 */
	public static final int GAME_WIDTH=1200;
	/**
	 * 游戏窗口初始高度，默认为600
	 */
	public static final int GAME_HEIGHT=800;
	/**
	 * 信息展示面板字体颜色
	 */
	public static final Color fontColor=Color.WHITE;
	public static final Color frameColor=Color.BLACK;
	private Image backScreenImage=null;
	private static Random r=new Random();
	private int level=0;
	private int totalLevel=4;
	private int rbTankCount=10;
	private int increaseRbTank=8;
	//创建一个Timer
	private Timer tcTimer=new Timer();
	//创建一个计时器任务
	private timerTask tcTask=new timerTask();
	private Dialog dg=new Dialog(this);
	private Button btOK=null;
	private PaintThread pt=new PaintThread();
	private Thread tt=new Thread(pt);
	
	Tank myTank=null;
	List<Missile> rbmissiles =new ArrayList<Missile>();
	List<Missile> missiles =new ArrayList<Missile>();
	List<Explode> explodes =new ArrayList<Explode>();
	List<RobotTank> RbTanks=new ArrayList<RobotTank>();
	List<Wall> walls=new ArrayList<Wall>();
	List<Blood> bloods=new ArrayList<Blood>();
	List<Bullet> bullets=new ArrayList<Bullet>();
	List<Shield> shields=new ArrayList<Shield>();
	List<SuperShield> superShields=new ArrayList<SuperShield>();
	/**
	 * 绘制游戏角色：Tank--主坦克
	 * RobotTank--机器坦克
	 * Missile--坦克炮弹
	 * Scores--玩家得分
	 */
	public void paint(Graphics g) {
		
		//绘制信息展示面板
		Color c=g.getColor();
		g.setColor(fontColor);
		g.setFont(new Font("Tahoma", Font.BOLD, 12));
		g.drawString("SuperFire Count: "+myTank.getSuperFireCount(),10,40);
		g.drawString("SuperShield Count: "+myTank.getSuperShieldCount(),10,60);
		g.drawString("Life Value: "+myTank.getLifeValue(),10,80);
		g.drawString("Life Count: "+(myTank.getLifeCount()-1),10,100);
		g.drawString("Game ScoreS : "+myTank.getScore(), 10, 120);
		g.drawString("Game Level : "+(level+1), 10, 140);
		g.drawString("RobotTank Count : "+RbTanks.size(), 10, 160);
		g.setColor(c);
		
		//绘制游戏角色
		if(walls!=null){
			for(int i=0;i<walls.size();i++){
				Wall w=walls.get(i);
				w.draw(g);
			}
		}
		if(missiles!=null){
			for(int i=0;i<missiles.size();i++){
				Missile m=missiles.get(i);	
				m.collidesWithWall(walls);
				if(m.hitTank(RbTanks)){
					myTank.setScore(myTank.getScore()+50);
				}
				m.draw(g);	
			}
		}
		if(rbmissiles!=null){
			for(int i=0;i<rbmissiles.size();i++){
				Missile m=rbmissiles.get(i);
				m.collidesWithWall(walls);
				m.collidesWithShield(superShields);
				m.hitTank(myTank);
				m.draw(g);	
			}
		}
		if(myTank!=null){
			//判断是否死亡及复活
			if(myTank.getLifeCount()>0&&!myTank.isLive()){
				if(r.nextInt(20)>18){
					myTank.setLifeCount(myTank.getLifeCount() - 1);
					myTank.setLifeValue(100);
					myTank.setLive(true);
				}
			}
			//判断是否退出
			if(myTank.getLifeCount()<=0){
				tcTimer.cancel();
				pt.shutDowan();
				initDialog("失败","Sorry,You Lost! ~ ~ 休息一下再玩吧!");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				dg.dispose();
				System.exit(0);
			}
			myTank.collidesWithWall(walls);
			myTank.collidesWithRbTank(RbTanks);
			myTank.eatBloods(bloods);
			myTank.eatBullets(bullets);
			myTank.eatShields(shields);
			myTank.draw(g);
		}
		if(RbTanks!=null){
				if(RbTanks.size()==0 && myTank.getLifeCount()>0){
					level++;
					if(level<totalLevel){
						InitRbTanks(rbTankCount+increaseRbTank*level);
					}else{
						level=0;
						tcTimer.cancel();
						pt.shutDowan();
						initDialog("获胜","Congratulation,You Win! ^ ^ 休息一下再玩吧!");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						dg.dispose();
						System.exit(0);
					}
				}
			for(int i=0;i<RbTanks.size();i++){
				RobotTank rbt=RbTanks.get(i);
				rbt.collidesWithWall(walls);
				rbt.collidesWithTank(myTank);
				rbt.collidesWithRbTank(RbTanks);
				rbt.draw(g);
			}
		}
		if(explodes!=null){
			for(int i=0;i<explodes.size();i++){
				Explode e=explodes.get(i);
				e.draw(g);
			}	
		}
		if(bloods!=null){
			for(int i=0;i<bloods.size();i++){
				Blood b=bloods.get(i);
				b.draw(g);
			}
		}
		if(bullets!=null){
			for(int i=0;i<bullets.size();i++){
				Bullet b=bullets.get(i);
				b.draw(g);
			}
		}
		if(shields!=null){
			for(int i=0;i<shields.size();i++){
				Shield s=shields.get(i);
				s.draw(g);
			}
		}
		if(superShields!=null){
			for(int i=0;i<superShields.size();i++){
				SuperShield ss=superShields.get(i);
				ss.draw(g);
			}
		}
		
		if(r.nextInt(1000)>997 && myTank.isLive()) Blood.InitBloods(bloods,this);
		if(r.nextInt(1000)<5 && myTank.isLive()) Bullet.InitBullets(bullets, this);
		if(r.nextInt(1000)>996 && myTank.isLive()) Shield.InitShields(shields, this);	
	}
	
	/**
	 * 重绘TANK，采用双缓冲解决屏幕闪烁
	 */
	public void update(Graphics g) {
		if(backScreenImage==null){
			backScreenImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics bg=backScreenImage.getGraphics();
		Color c=bg.getColor();
		bg.setColor(frameColor);
		bg.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		bg.setColor(c);
		paint(bg);
		g.drawImage(backScreenImage,0,0,null);
	}
	/**
	 * 初始化GAME工作区,初始化游戏窗口
	 */
	public void lauchFrame(){
		Dimension winSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(winSize.width/2-GAME_WIDTH/2,winSize.height/2-GAME_HEIGHT/2);
		this.setSize(GAME_WIDTH,GAME_HEIGHT);
		this.setResizable(false);
		this.setTitle("TankWar");
		this.setBackground(frameColor);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);
		this.addKeyListener(new KeyMonitor());
		initGame();
	}

	/*
	 * 重绘调用线程类
	 * 
	 */
	private class PaintThread implements Runnable{
		private int sleepTime=50;
		private boolean dead=false;
		public void run() {
		while(!dead){
			repaint();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	  }	
		public void shutDowan(){
			dead=true;
		}
	}
	/*
	 * 键盘监听器类
	 *
	 */
	private class KeyMonitor extends KeyAdapter{

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
		
	}
	private class timerTask extends TimerTask{

		public void run() {
			if(superShields!=null){
				for(int i=0;i<superShields.size();i++){
					superShields.remove(i);
				}
			}
			
		}
		
	}
	/*
	 * 初始化主战坦克
	 */
	private void InitTank(){
		while(true){
			int xPos=r.nextInt(TankClient.GAME_WIDTH-200)+100;
			int yPos=r.nextInt(TankClient.GAME_HEIGHT-200)+80;
			if(xPos<0 || xPos>TankClient.GAME_WIDTH){
				xPos=50;
			}
			if( yPos<0 || yPos>TankClient.GAME_HEIGHT){
				yPos=50;
			}
			Rectangle rect=new Rectangle(xPos,yPos,Tank.TANK_WIDTH,Tank.TANK_HEIGHT);
			if(!collideWithRect(rect,true)){
				myTank=new Tank(xPos,yPos,this);
				break;
			}
		}
		
	}
	/*
	 * 初始化机器坦克
	 */
	public void InitRbTanks(int count){
		int xPos=r.nextInt(TankClient.GAME_WIDTH);
		int yPos=r.nextInt(TankClient.GAME_HEIGHT);
		for(int i=0;i<count;i++){
			if(xPos<0 || xPos+RobotTank.TANK_WIDTH>TankClient.GAME_WIDTH || yPos<0 || yPos+RobotTank.TANK_HEIGHT>TankClient.GAME_HEIGHT){
				continue;
			}
			if(collideWithRect(new Rectangle(xPos,yPos,RobotTank.TANK_WIDTH,RobotTank.TANK_HEIGHT),false)){
				continue;
			}
			RbTanks.add(new RobotTank(xPos,yPos,this));
			xPos=r.nextInt(TankClient.GAME_WIDTH);
			yPos=r.nextInt(TankClient.GAME_HEIGHT);
		}
	}
	/*
	 * 初始化游戏地图
	 */
	private void Maps(){
		int wallCount=r.nextInt(3)+1;
		int wn=0,oldWn=0;
		Wall w1=new Wall(150,100,360,10,this);
		Wall w2=new Wall(180,180,10,300,this);
		Wall w3=new Wall(450,230,10,300,this);
		Wall w4=new Wall(330,380,300,10,this);
		Map<Integer, Wall> map=new HashMap<Integer, Wall>();
		map.put(1, w1);
		map.put(2, w2);
		map.put(3, w3);
		map.put(4, w4);
		for(int i=0;i<wallCount;i++){
			wn=r.nextInt(3)+1;
			if(wn == oldWn) continue;
			walls.add(map.get(wn));
			oldWn=wn;
		}
	}
	
	private void initGame(){
		totalLevel=Integer.parseInt(PropertyMgr.getProperty("gameLevel"));
		if(totalLevel<2){
			totalLevel=2;
		}
		rbTankCount=Integer.parseInt(PropertyMgr.getProperty("initRbTankCount"));
		if(rbTankCount<3){
			rbTankCount=3;
		}
		increaseRbTank=Integer.parseInt(PropertyMgr.getProperty("increaseRbTankCount"));
		if(increaseRbTank<3){
			increaseRbTank=3;
		}
		//这样初始化可以减少碰撞检测量
		Maps();
		InitTank();
		InitRbTanks(rbTankCount);
		tt.start();
		tcTimer.schedule(tcTask, 5000,10000);
	}
	public boolean collideWithRect(Rectangle rect,boolean choice){
		if(choice){
			if(walls!=null){
				for(int i=0;i<walls.size();i++){
					Wall w=walls.get(i);
					if(w.getRect().intersects(rect)){
						return true;
					}
				}
				return false;
			}
			return false;
		}else{
			if(myTank!=null){
				if(myTank.getRect().intersects(rect)){
					return true;
				}
				return false;
			}
			return false;
		}
	}
	public static void main(String[] args){
		TankClient tc=new TankClient();
		tc.lauchFrame();
	}
	public void initDialog(String title,String content){
		Label lb=new Label();
		dg.setSize(200,120);
		dg.setTitle(title);
		btOK=new Button("退出游戏");
		int fxPos=this.getX();
		int fyPos=this.getY();
		int dxPos=fxPos+this.getWidth()/2-dg.getWidth()/2;
		int dyPos=fyPos+this.getHeight()/2-dg.getHeight()/2;
		lb.setText(content);
//		lb.setSize(150,50);
		btOK.setSize(30, 20);
		btOK.addActionListener(this);
		dg.add(lb, BorderLayout.CENTER);
//		dg.add(btOK,BorderLayout.EAST);
		dg.setLocation(dxPos,dyPos);
		dg.setResizable(false);
		dg.setVisible(true);
		dg.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btOK){
			tcTimer.cancel();
			dg.dispose();
			System.exit(0);
		}
	}
	
}
