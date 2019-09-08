import java.util.Random;
public class IntllgntRandom implements IIntelligent{
	private Tank tank;
	public void setTank(Tank tank){
		this.tank = tank;
	}
	public int getDirection(int x,int y,int direction){
		Random rnd = new Random();
		if(rnd.nextInt(10)==1){
			direction = rnd.nextInt(4)+1;
		}		
		return direction;
	}
}
