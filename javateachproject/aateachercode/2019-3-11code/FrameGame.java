
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
public class FrameGame extends JFrame {
	private Tank tank;
	public FrameGame(){
		super("TankWar");
		setSize(800,600);
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tank = new Tank();		
		Thread t = new FreshThread();
		t.start();
	}
	
	public void paint(Graphics g){		
		super.paint(g);		
		tank.draw(g);
	}
	private class FreshThread extends Thread{
		public void run(){
			while(true){
				try{
					Thread.sleep(100);
					tank.go();
					repaint();				
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
