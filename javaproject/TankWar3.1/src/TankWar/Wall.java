package TankWar;

import java.awt.*;

/**
 * ��ͼ��ǽ��
 * @author qbg
 *
 */
public class Wall {
	private static final Color WALLCOLOR = Color.DARK_GRAY;
	private int x,y;
	private int wallWidth,wallHeight;
	/**
	 * ǽ���캯��
	 * @param x ��ʼ��Xλ��
	 * @param y ��ʼ��Yλ��
	 * @param width ��ʼ�����
	 * @param height ��ʼ���߶�
	 * @param tc ��ܼ�-TankClient
	 */
	public Wall(int x,int y,int width,int height,TankClient tc){
		this.x=x;
		this.y=y;
		this.wallWidth=width;
		this.wallHeight=height;
	}
	/**
	 * ǽ�滭����
	 * @param g ����
	 */
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(WALLCOLOR);
		g.fillRect(x, y, wallWidth, wallHeight);
		g.setColor(c);
	}
	/**
	 * ǽ��ײ����������
	 * @return ��������
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,wallWidth,wallHeight);
	}
}
