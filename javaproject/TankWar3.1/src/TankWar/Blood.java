package TankWar;

import java.awt.*;
import java.util.List;
import TankWar.Direction;

/**
 * ��ѪѪ����-�̳���Good
 * @author qbg
 *
 */
public class Blood extends Good {
	private static int bloodsCount=2;
	/**
	 * Ѫ�鹹�캯��
	 * @param x ��ʼXλ��
	 * @param y ��ʼYλ��
	 * @param dir ��ʼ���з���
	 * @param tc ��ܼ�--TankClient
	 */
	public Blood(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, tc);
	}
	/**
	 * Ѫ��滭����
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
	 * ����Ѫ��Ⱥ
	 * @param bloods Ѫ��Ⱥ
	 * @param tc ��ܼ�-TankClient
	 * @return Ѫ������
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
