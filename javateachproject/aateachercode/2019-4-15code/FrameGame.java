
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
public class FrameGame extends JFrame {
	private Tank tank;
	private Bullet bullet=null;
	private ArrayList pBullets =new ArrayList();
	private ArrayList aBullets =new ArrayList();
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
		//Íæ¼ÒÅÚµ¯
		for(int i=0;i<pBullets.size();i++){
			Bullet a = (Bullet)pBullets.get(i);
			a.draw(gOffScreen);
		}
	
		for(int i=0;i<autoTanks.size();i++){
			AutoTank a = (AutoTank)autoTanks.get(i);
			a.draw(gOffScreen);
		}
		//µçÄÔÅÚµ¯
		for(int i=0;i<aBullets.size();i++){
			Bullet a = (Bullet)aBullets.get(i);
			a.draw(gOffScreen);
		}
		//±¬Õ¨		
		for(int i=0;i<explodes.size();i++){
			Explode e = (Explode)explodes.get(i);
			if(e.getAlive()){
				e.draw(gOffScreen);
			}else{
				explodes.remove(e);
			}
		}
		g.drawImage(offScreenImage,0,0,null);
	}
	private boolean hitBorder(int x,int y){
		if(x>30&& x<770&&y>50 && y<570){
			return false;		
		}else{
			return true;
		}			
	}
	private class FreshThread extends Thread{
		public void run(){
			while(true){
				try{
					Thread.sleep(100);					
					if(!hitBorder(tank.getNextPositon().x,tank.getNextPositon().y)){
						tank.go();
					}
					//Íæ¼ÒÅÚµ¯
					for(int i=0;i<pBullets.size();i++){
						Bullet b = (Bullet)pBullets.get(i);
						if(hitBorder(b.getX(),b.getY())){
							pBullets.remove(b);
							explodes.add(new Explode(b.getX(),b.getY()));
							continue;
						}
						for(int j=0;j<autoTanks.size();j++){
							AutoTank a = (AutoTank)autoTanks.get(j);
							if(b.getBounds().intersects(a.getBounds())){
								autoTanks.remove(a);
								pBullets.remove(b);
								explodes.add(new Explode(a.getX(),a.getY()));
								continue;
							}
						}
						b.go();
					}
					for(int i=0;i<autoTanks.size();i++){
						AutoTank a = (AutoTank)autoTanks.get(i);
						a.go();
						Bullet b = a.fire();
						if(b != null){
							aBullets.add(b);
						}
					}		
					//µçÄÔÅÚµ¯
					for(int i=0;i<aBullets.size();i++){
						Bullet a = (Bullet)aBullets.get(i);
						a.go();
					}
					repaint();	
					if(tankCoolTime==50){
						AutoTank aTank = new AutoTank();
						Random random = new Random();
						int i = random.nextInt(3);//up
						aTank.setX(map.getPoints()[i].x);
						aTank.setY(map.getPoints()[i].y);
						Random rnd = new Random();
						if(rnd.nextInt(5)==1){
							IntllgntFollow intll = new IntllgntFollow();
							intll.setTank(tank);
							aTank.setStrategy(intll);
						}else{
							IntllgntRandom intll = new IntllgntRandom();
							intll.setTank(tank);
							aTank.setStrategy(intll);							
						}
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
					Bullet b= tank.fire();
					if(b != null)
						pBullets.add(b);
			};	
			repaint();
		}
	}
}
