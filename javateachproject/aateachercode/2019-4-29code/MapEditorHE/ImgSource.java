package MapEditor;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
public class ImgSource {
    private static ImgSource imgs=null;
    private static Image image=null;
	public ImgSource() {
		initImage();
	}
	public static ImgSource getInstance() {
		if(imgs==null) {
			imgs=new ImgSource();
		}
		return imgs;
	}
	public void initImage() {
		File f = new File("insect_sprite.png");
		try{
			image = ImageIO.read(f);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void drawWater(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 7*34;
		imagex2 = imagex1+33;
		imagey2 = imagey1+33;		
		g.drawImage(image,x,y,x+33,y+33,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawGrass(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 4*34;
		imagey1 = 7*34;
		imagex2 = imagex1+33;
		imagey2 = imagey1+33;		
		g.drawImage(image,x,y,x+33,y+33,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawBrick(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 18*34;
		imagey1 = 5*34;
		imagex2 = imagex1+17;
		imagey2 = imagey1+17;		
		g.drawImage(image,x,y,x+17,y+17,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawIron(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 6*34;
		imagex2 = imagex1+17;
		imagey2 = imagey1+17;		
		g.drawImage(image,x,y,x+17,y+17,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawPtankhome(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 19*34;
		imagey1 = 5*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,x,y,x+34,y+34,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawPtank(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0*34;
		imagey1 = 0*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,x,y,x+34,y+34,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawAtankhome(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 21*34;
		imagey1 = 5*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,x,y,x+34,y+34,imagex1,imagey1,imagex2,imagey2,null);		
	}
	public void drawAtank(Graphics g,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0*34;
		imagey1 = 1*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,x,y,x+34,y+34,imagex1,imagey1,imagex2,imagey2,null);		
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
