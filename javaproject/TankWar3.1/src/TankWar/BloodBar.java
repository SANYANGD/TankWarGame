package TankWar;

import java.awt.*;

/**
 * ̹������Ѫ����ʾ��
 * @author qbg
 *
 */
public class BloodBar {
	/**
	 * Ѫ���ʼ��ɫ��Ĭ��Ϊ�ٻ�ɫ
	 */
	public Color bloodBarColor=Color.ORANGE;
	private int x,y,width,height,bWidth;
	
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setbWidth(int bWidth) {
		this.bWidth = bWidth;
	}
	/**
	 * Ѫ�鹹�캯��
	 * @param x ��ʼXλ��
	 * @param y ��ʼYλ��
	 * @param width ��ʼ���
	 * @param height ��ʼ�߶�
	 */
	public BloodBar(int x,int y,int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.bWidth=width;
		this.height=height;
	}
	/**
	 * Ѫ��滭����
	 * @param g ����
	 */
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(bloodBarColor);
		g.drawRect(x, y, width, height);
		g.fillRect(x, y, bWidth, height);
		g.setColor(c);
	}
	
}
