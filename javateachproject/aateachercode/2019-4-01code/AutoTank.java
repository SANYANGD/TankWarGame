import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Random;

public class AutoTank extends Tank{

	
	public AutoTank(){
		super(400,50,34,34);
		Random random = new Random();
		int direction = random.nextInt(3)+2;//up
		setDirection(direction);
	}
	public void draw(Graphics g){	
		
		ImgSource.getInstance().drawAutoTank(g,state,getX(),getY(),getDirection());
		
	}
	public void go(){
		goForward();
	}
	
}
