import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MapEditor.ImgSource;

import java.util.Random;

public class AutoTank extends Tank{

	private int fireCoolTime = 0;
	private int StragegyCoolTime = 0;
	private IIntelligent strategy = null;
	public AutoTank(){
		super(400,50,34,34);
		Random random = new Random();
		int direction = random.nextInt(3)+2;//up
		setDirection(direction);
	}
	public void setStrategy(IIntelligent strategy){
		this.strategy = strategy;
	}
	public void draw(Graphics g){			
		ImgSource.getInstance().drawAutoTank(g,state,getX(),getY(),getDirection());
	}
	public void go(){
		fireCoolTime++;
		StragegyCoolTime++;
		if(StragegyCoolTime>20){
			setDirection(strategy.getDirection(getX(),getY(),getDirection()));
			StragegyCoolTime = 0;
		}
		goForward();
	}
	public Bullet fire(){
		Bullet b = null;
		if(fireCoolTime>20){
			Random r = new Random();
			int i = r.nextInt(5);
			if(i==1){			
				b = super.fire();
			}
			fireCoolTime = 0;
		}
		return b;		
	}
}
