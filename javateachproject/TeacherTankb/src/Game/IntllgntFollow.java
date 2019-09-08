package Game;

import java.util.Random;

public class IntllgntFollow implements IIntelligent{
	Tank tank;
	@Override
	public void setTank(Tank tank) {
		this.tank = tank;		
	}

	@Override
	public int getDirection(int x, int y, int direction) {
		Random random = new Random();
		int i = random.nextInt(4);
		if(i==3) {
			if(Math.abs(tank.getX()-x)>Math.abs(tank.getY()-y)) {
				if(tank.getX()>x) {
					direction = 2;//right
				}else {
					direction = 4;
				}
			}else {
				if(tank.getY()>y) {
					direction = 3;
				}else {
					direction = 1;
				}
			}
		}
		return direction;
	}

}
