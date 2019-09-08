import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class GameFrame extends JFrame{

	int x,y;
	
	public GameFrame(){
		setSize(800,600);
		setResizable(false);
		setLocation(200,200);
		x = 100;
		y = 100;
		
	}
	public void run(){
		GameFrameThread t = new GameFrameThread();
		
		t.run() ;
	}
	public void paint(Graphics g){
		super.paint(g);		
		
		g.drawRect(x,y,3,20);
		g.drawRect(x+17,y,3,20);
		g.drawRect(x+3,y+3,14,14);
		g.drawOval(x+5,y+5,10,10);
		g.drawRect(x+9,y,3,10);			
	}
	private class GameFrameThread extends Thread{
		public void run(){
			for(int i=0;i<1000;i++){
				try{
					y = y-2;
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}		
				repaint();
			}
		}
	}


}
