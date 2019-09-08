package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImgSource {
	private static ImgSource imgs=null;
	private static Image image=null;
	int imagex1,imagey1,imagex2,imagey2;
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
	public void drawPlayerTank(Graphics g,int x,int y){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x,y,
				x+34,y+34,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawAutoTank(Graphics g,int x,int y){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x,y,
				x+34,y+34,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawExplode(Graphics g,int state,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (state+20)*34;
		imagey1 = 4*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawSpade(Graphics g,int x,int y) {
		drawElement(g,x,y,24,7,34);		
	}
	public void drawWater(Graphics g,int x,int y) {
		drawElement(g,x,y,0,7,34);			
	}
	public void drawGrass(Graphics g,int x,int y) {
		drawElement(g,x,y,4,7,34);		
	}
	public void drawBrick(Graphics g,int x,int y) {
		drawElement(g,x,y,18,5,34);			
	}
	public void drawIron(Graphics g,int x,int y) {
		drawElement(g,x,y,0,6,17);			
	}
	public void drawBase(Graphics g,int x,int y) {
		drawElement(g,x,y,19,5,34);	
		
	}
	
	public void drawElement(Graphics g,int x,int y,int imgX,int imgY,int width) {
		
		g.drawImage(image,
				x,y,
				x+width,y+width,
				imgX,imgY,
				imgX+width,imgY+width,null);
	}	
	
}
