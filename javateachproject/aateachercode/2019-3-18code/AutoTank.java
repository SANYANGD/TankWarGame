import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Random;

public class AutoTank {
	private int x,y;
	private int width,height;
	private int direction;
	private int state = 0;
	private Image image=null;
	public AutoTank(){
		x = 400;
		y = 50;
		width = 34;
		height = 34;
		Random random = new Random();
		direction = random.nextInt(3)+2;//up
		File f = new File("insect_sprite.png");
		
		try{
			image = ImageIO.read(f);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*width*2;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = 34;
		if(state==1){
			imagex1 += 34;
			imagex2 +=34;
		}			
		g.drawImage(image,
				x-width/2,y-height/2,
				x+width/2,y+height/2,
				imagex1,imagey1,
				imagex2,imagey2,null);		
	}
	public void setDirection(int dir){
		if(dir>0 && dir<=4){
			direction = dir;
		}
		
	}
	public void go(){
		if(state == 0){
			state = 1;
		}else{
			state = 0;
		}
		int ax,ay;
		ax = x;
		ay = y;
		switch(direction){
		case 1:
			ay = ay-1;break;
		case 2:
			ax = ax+1;break;
		case 3:
			ay = ay+1;break;
		case 4:
			ax = ax-1;break;
		}
		
		if(ax>width/2 && ax<800-width/2&&ay>height/2 && ay<600-height/2){
			
			x = ax;
			y = ay;
		}
	}
}
