
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;
public class FrameGame extends JFrame {
	private Tank tank;
	//private ArrayList<AutoTank> autoTank=new ArrayList<AutoTank>();
	private ArrayList autoTanks=new ArrayList();
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
		tank.draw(gOffScreen);
		for(int i=0;i<autoTanks.size();i++){
			AutoTank a = (AutoTank)autoTanks.get(i);
			a.draw(gOffScreen);
		}
		g.drawImage(offScreenImage,0,0,null);
	}
	private class FreshThread extends Thread{
		public void run(){
			while(true){
				try{
					Thread.sleep(100);
					tank.go();
					for(int i=0;i<autoTanks.size();i++){
						AutoTank a = (AutoTank)autoTanks.get(i);
						a.go();
					}					
					repaint();	
					if(tankCoolTime==50){
						AutoTank aTank = new AutoTank();
						Random random = new Random();
						int i = random.nextInt(3);//up
						aTank.setX(map.getPoints()[i].x);
						aTank.setY(map.getPoints()[i].y);
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
			};	
			repaint();
		}
	}
}
