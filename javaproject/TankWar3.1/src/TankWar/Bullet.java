package TankWar;

import java.awt.*;
import java.util.List;
import TankWar.Direction;

/**
 * 超级炮弹奖励类--继承于Good
 * @author qbg
 *
 */
public class Bullet extends Good {
	private static int bulletsCount=2;
	/**
	 * 初始化函数
	 * @param x 初始化X位置
	 * @param y 初始化Y位置
	 * @param dir 初始化飞行方向
	 * @param tc 大管家-TankClient
	 */
	public Bullet(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, tc);
	}
	/**
	 * 超级炮弹绘画函数
	 */
	public void draw(Graphics g){
		if(!this.isLive()){
			Bullet.tc.bullets.remove(this);
			return ;
		}
		Color c=g.getColor();
		g.setColor(this.getColor());
		g.fillOval(this.getX(), this.getY(), this.getGWIDTH(), this.getGHEIGHT());
		g.setColor(c);
		
		this.move();
	}
	/**
	 * 生成超级炮弹群
	 * @param bullets 超级炮弹群
	 * @param tc 大管家-TankClient
	 * @return 超级炮弹数量
	 */
	public static int InitBullets(List<Bullet> bullets,TankClient tc){
		bulletsCount=Integer.parseInt(PropertyMgr.getProperty("bulletCount"));
		if(bulletsCount<1){
			bulletsCount=1;
		}
		int xPos=Bullet.r.nextInt(TankClient.GAME_WIDTH-100)+50;
		int yPos=Bullet.r.nextInt(TankClient.GAME_HEIGHT-100)+50;
		int bulletCount=Blood.r.nextInt(bulletsCount)+1;
		for(int i=0;i<bulletCount;i++){
			Bullet b=new Bullet(xPos,yPos,Direction.RD,tc);
			b.setColor(Color.CYAN);
			b.setShape(15, 15);
			b.setSpeed(5, 5);
			bullets.add(b);
		}
		return bulletCount;
	}

}
