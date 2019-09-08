import java.util.Random;

public class IntllgntFollow implements IIntelligent{
	private Tank tank;
	public void setTank(Tank tank){
		this.tank = tank;
	}
	public int getDirection(int x,int y,int direction){
		Random rnd = new Random();
		if(rnd.nextInt(10)==1){
			if(Math.abs(x-tank.getX())>Math.abs(y-tank.getY())){
				if(x>tank.getX()){
					direction = 4;
				}else{
					direction = 2;
				}					
			}else{
				if(y>tank.getY()){
					direction = 1;
				}else
					direction = 3;				
			}		
		}		
		return direction;
	}
}
