package TankWar;

import java.awt.*;
import java.util.List;
import TankWar.Direction;

/**
 * 加血血块类-继承于Good
 * @author qbg
 *
 */
public class Blood extends Good {
	private static int bloodsCount=2;
	/**
	 * 血块构造函数
	 * @param x 初始X位置
	 * @param y 初始Y位置
	 * @param dir 初始飞行方向
	 * @param tc 大管家--TankClient
	 */
	public Blood(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, tc);
	}
	/**
	 * 血块绘画函数
	 */
	public void draw(Graphics g){
		if(!this.isLive()){
			Blood.tc.bloods.remove(this);
			return ;
		}
		Color c=g.getColor();
		g.setColor(this.getColor());
		g.fillOval(this.getX(), this.getY(), this.getGWIDTH(), this.getGHEIGHT());
		g.setColor(c);
		
		this.move();
	}
	/**
	 * 生成血块群
	 * @param bloods 血块群
	 * @param tc 大管家-TankClient
	 * @return 血块数量
	 */
	public static int InitBloods(List<Blood> bloods,TankClient tc){
		bloodsCount=Integer.parseInt(PropertyMgr.getProperty("bloodCount"));
		if(bloodsCount<1){
			bloodsCount=1;
		}
		int xPos=Blood.r.nextInt(TankClient.GAME_WIDTH-100)+100;
		int yPos=Blood.r.nextInt(TankClient.GAME_HEIGHT-100)+50;
		int bloodCount=Blood.r.nextInt(bloodsCount)+1;
		for(int i=0;i<bloodCount;i++){
			Blood b=new Blood(xPos,yPos,Direction.RU,tc);
			b.setColor(Color.RED);
			bloods.add(b);
		}
		return bloodCount;
	}

}
