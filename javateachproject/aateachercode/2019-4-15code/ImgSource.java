import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgSource {
	private static ImgSource imgs=null;
	private static Image image=null;
	private ImgSource(){
		initImage();
	}
	public static ImgSource getInstance(){
		if(imgs == null){
			imgs = new ImgSource();
		}
		return imgs;
	}
	
	public void initImage(){
		File f = new File("insect_sprite.png");
		
		try{
			image = ImageIO.read(f);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void drawBullet(Graphics g,int state,int x,int y,int direction){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 5*34;
		imagey1 = 6 *34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawPlayerTank(Graphics g,int state,int x,int y,int direction){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawAutoTank(Graphics g,int state,int x,int y,int direction){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34;
		imagey1 = 34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawExplode(Graphics g,int state,int x,int y){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (20+state)*34;
		imagey1 = 4*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
}
