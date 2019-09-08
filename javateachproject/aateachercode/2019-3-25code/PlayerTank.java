import java.awt.Graphics;
import java.util.Random;

public class PlayerTank extends Tank{
	public PlayerTank(){
		super(100,100,34,34);	
		setDirection(1);
	}
	public void draw(Graphics g){
		drawTank(g,0);
	}

}
