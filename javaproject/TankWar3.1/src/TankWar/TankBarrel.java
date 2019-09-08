package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import TankWar.Direction;

/**
 * Tank��Ͳ��
 * @author qbg
 *
 */
public class TankBarrel{
	/**
	 * ̹����Ͳ��ʼ����Ĭ��Ϊ���·�
	 */
	public Direction brDir=Direction.D;
	/**
	 * ̹����ͲXλ��
	 */
	public int x;
	/**
	 * ̹����ͲYλ��
	 */
	public int y;
	/*
	 * TankͼƬ��������Ͳ����任
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	static {
		tankImages = new Image[] {
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
				tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif"))
		};
		
		imgs.put("L", tankImages[0]);
		imgs.put("LU", tankImages[1]);
		imgs.put("U", tankImages[2]);
		imgs.put("RU", tankImages[3]);
		imgs.put("R", tankImages[4]);
		imgs.put("RD", tankImages[5]);
		imgs.put("D", tankImages[6]);
		imgs.put("LD", tankImages[7]);
		
	}
	/**
	 * ��Ͳ�滭����
	 * @param g ����
	 */
	public void draw(Graphics g){
		switch(brDir) {
		case L:
			g.drawImage(imgs.get("L"),x,y,null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"),x,y,null);
			break;
		case U:
			g.drawImage(imgs.get("U"),x,y,null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"),x,y,null);
			break;
		case R:
			g.drawImage(imgs.get("R"),x,y,null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"),x,y,null);
			break;
		case D:
			g.drawImage(imgs.get("D"),x,y,null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"),x,y,null);
			break;
		}
	}
}