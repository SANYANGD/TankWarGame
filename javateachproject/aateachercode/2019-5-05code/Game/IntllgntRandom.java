package Game;

import java.util.Random;

public class IntllgntRandom implements IIntelligent{
	Tank tank;
	public void setTank(Tank tank) {
		this.tank = tank;
	};
	public int getDirection(int x,int y,int direction) {
		Random random = new Random();
		int i = random.nextInt(4);
		if(i==3) {
			direction = random.nextInt(4)+1;
		}
		return direction;
	}	
}
