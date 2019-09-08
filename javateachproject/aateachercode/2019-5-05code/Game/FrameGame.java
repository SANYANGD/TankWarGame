package Game;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
public class FrameGame extends JFrame {
	private Tank tank;

	private ArrayList playerBullets =new ArrayList();
	private ArrayList autoBullets = new ArrayList();
	//private ArrayList<AutoTank> autoTank=new ArrayList<AutoTank>();
	private ArrayList autoTanks=new ArrayList();
	private ArrayList explodes=new ArrayList();
	private Map map = new Map();
	private int tankCoolTime = 0;
	public FrameGame(){
		super("TankWar");
		setSize(800,600);
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tank = new PlayerTank();
		tank.setX(map.getMainPoint().x);
		tank.setY(map.getMainPoint().y);
		Thread t = new FreshThread();
		t.start();
	}
	private Image offScreenImage = null;
	private Graphics gOffScreen = null;
	public void paint(Graphics g){
		if(offScreenImage==null){
			offScreenImage = this.createImage(800,600);
			gOffScreen = offScreenImage.getGraphics();
		}
		super.paint(gOffScreen);
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0,0,800,600);
		tank.draw(gOffScreen);
		for(int i=0;i<playerBullets.size();i++){
			Bullet b = (Bullet)playerBullets.get(i);
			b.draw(gOffScreen);
		}
	
		for(int i=0;i<autoTanks.size();i++){
			AutoTank a = (AutoTank)autoTanks.get(i);
			a.draw(gOffScreen);
		}
		for(int i=0;i<autoBullets.size();i++){
			Bullet b = (Bullet)autoBullets.get(i);
			b.draw(gOffScreen);
		}
		for(int i=0;i<explodes.size();i++) {
			Explode e = (Explode)explodes.get(i);
			if(e.getAlive()) {
				e.draw(gOffScreen);
			}else {
				explodes.remove(e);
			}
		}
		g.drawImage(offScreenImage,0,0,null);
	}
	private boolean hitBorder(Tank obj) {
		Point point;
		point = obj.getNextPosition();
		if(point.x<17 || point.x>800-17 ||
				point.y<17 || point.y>600-17){
			return true;			
		}else {
			return false;
		}
	}
	private class FreshThread extends Thread{
		public void run(){
			while(true){
				try{
					Thread.sleep(100);
					if(!hitBorder(tank)){
						tank.go();
					}
					for(int i=0;i<playerBullets.size();i++){
						Bullet b = (Bullet)playerBullets.get(i);
						if(hitBorder(b)){
							explodes.add(new Explode(b.getX(),b.getY()));
							playerBullets.remove(b);
							continue;
						}
						for(int j=0;j<autoTanks.size();j++){
							AutoTank a = (AutoTank)autoTanks.get(j);							
							if(b.hit(a.getBounds())){
								explodes.add(new Explode(a.getX(),a.getY()));
								playerBullets.remove(b);
								autoTanks.remove(a);	
								continue;
							}
						}						
						b.go();						
					}
					
					for(int i=0;i<autoTanks.size();i++){
						AutoTank a = (AutoTank)autoTanks.get(i);
						if(!hitBorder(a)) {
							a.go();
						}
						Bullet b = a.fire();
						if(b != null) {
							autoBullets.add(b);
						}
					}	
				
					for(int i=0;i<autoBullets.size();i++){
						Bullet b = (Bullet)autoBullets.get(i);
						if(hitBorder(b)){
							explodes.add(new Explode(b.getX(),b.getY()));
							autoBullets.remove(b);
							continue;
						}									
						b.go();	
					}
					repaint();	
					if(tankCoolTime==50){
						AutoTank aTank = new AutoTank();
						Random random = new Random();
						int i = random.nextInt(3);//up
						aTank.setX(map.getPoints()[i].x);
						aTank.setY(map.getPoints()[i].y);
						aTank.setGoCategory(IntllgntFactory.getGoCategory(tank));
						autoTanks.add(aTank);
						tankCoolTime=0;
					}else{
						tankCoolTime++;
					}
				}catch(InterruptedException e){
					e.printStackTrace();				
				}
			}
		}
	}
	private class KeyMonitor extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_UP:
					tank.setDirection(1);break;
				case KeyEvent.VK_RIGHT:
					tank.setDirection(2);break;
				case KeyEvent.VK_DOWN:
					tank.setDirection(3);break;
				case KeyEvent.VK_LEFT:
					tank.setDirection(4);break;
				case KeyEvent.VK_SPACE:
					Bullet bullet = tank.fire();
					if(bullet != null) {
						playerBullets.add(bullet);
					}
			};	
			repaint();
		}
	}
}
