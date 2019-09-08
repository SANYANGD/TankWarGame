package TankWar;

import java.awt.*;
import java.util.List;
import TankWar.Direction;

/**
 * 超级防护罩奖励类--继承于Good
 * 吃了可以产生挡子弹的超级防护罩类
 * @author qbg
 *
 */
public class Shield extends Good {
	
	private static int shieldsCount=2;
	/**
	 * 初始化函数
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param dir 初始化飞行方向
	 * @param tc 大管家-TankClient
	 */
	public Shield(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, tc);
	}
	/**
	 * 超级防护罩绘画函数
	 */
	public void draw(Graphics g){
		if(!this.isLive()){
			Shield.tc.shields.remove(this);
			return ;
		}
		Color c=g.getColor();
		g.setColor(this.getColor());
		g.fillOval(this.getX(), this.getY(), this.getGWIDTH(), this.getGHEIGHT());
		g.setColor(c);
		
		this.move();
	}
	/**
	 * 生成超级防护罩群
	 * @param shields 超级防护罩群
	 * @param tc 大管家-TankClient
	 * @return 超级防护罩数量
	 */
	public static int InitShields(List<Shield> shields,TankClient tc){
		shieldsCount=Integer.parseInt(PropertyMgr.getProperty("shieldCount"));
		if(shieldsCount<1){
			shieldsCount=1;
		}
		int xPos=Shield.r.nextInt(TankClient.GAME_WIDTH-100)+50;
		int yPos=Shield.r.nextInt(TankClient.GAME_HEIGHT-100)+50;
		int shieldCount=Shield.r.nextInt(shieldsCount)+1;
		for(int i=0;i<shieldCount;i++){
			Shield s=new Shield(xPos,yPos,Direction.U,tc);
			s.setColor(Color.GREEN);
			s.setShape(12, 12);
			s.setSpeed(4, 4);
			shields.add(s);
		}
		return shieldCount;
	}

}
