package Game;

import java.util.Random;

public class IntllgntFactory {
	private static IntllgntFollow intllgntFollow=new IntllgntFollow();
	private static IntllgntRandom intllgntRandom=new IntllgntRandom();
	public static IIntelligent getGoCategory(Tank tank) {
		Random random = new Random();
		int i = random.nextInt(4);
		if(i==1) {
			intllgntFollow.setTank(tank);
			return intllgntFollow;
		}else {
			intllgntRandom.setTank(tank);
			return intllgntRandom;
		}
	}
}
