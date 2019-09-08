package TankWar;

import java.awt.*;
import java.util.List;
import TankWar.Direction;

/**
 * ���������ֽ�����--�̳���Good
 * ���˿��Բ������ӵ��ĳ�����������
 * @author qbg
 *
 */
public class Shield extends Good {
	
	private static int shieldsCount=2;
	/**
	 * ��ʼ������
	 * @param x ��ʼ��Xλ��
	 * @param y ��ʼ��Yλ��
	 * @param dir ��ʼ�����з���
	 * @param tc ��ܼ�-TankClient
	 */
	public Shield(int x, int y, Direction dir, TankClient tc) {
		super(x, y, dir, tc);
	}
	/**
	 * ���������ֻ滭����
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
	 * ���ɳ���������Ⱥ
	 * @param shields ����������Ⱥ
	 * @param tc ��ܼ�-TankClient
	 * @return ��������������
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
