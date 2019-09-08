package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

public class MissileImages {
	private int x,y;
	private Direction miDir;
	private boolean tankType = true;
	private static Toolkit mi = Toolkit.getDefaultToolkit();
	private static Image[] MissileImages = null;
	private static Map<String, Image> zimgs = new HashMap<String, Image>();
	private static Map<String,Image> imgs = new HashMap<String, Image>();
	static {
		MissileImages = new Image[] {
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileL.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileLU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileRU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileR.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileRD.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileD.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/zmissileLD.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileL.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileLU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileRU.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileR.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileRD.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileD.gif")),
				mi.getImage(MissileImages.class.getClassLoader().getResource("images/missileLD.gif"))
		};
		//Ö÷Õ½Ì¹¿Ë·¢ÉäÅÚµ¯Í¼Æ¬
		zimgs.put("L", MissileImages[0]);
		zimgs.put("LU", MissileImages[1]);
		zimgs.put("U", MissileImages[2]);
		zimgs.put("RU", MissileImages[3]);
		zimgs.put("R", MissileImages[4]);
		zimgs.put("RD", MissileImages[5]);
		zimgs.put("D", MissileImages[6]);
		zimgs.put("LD", MissileImages[7]);
		//»úÆ÷Ì¹¿Ë·¢ÉäÅÚµ¯Í¼Æ¬
		imgs.put("L", MissileImages[8]);
		imgs.put("LU", MissileImages[9]);
		imgs.put("U", MissileImages[10]);
		imgs.put("RU", MissileImages[11]);
		imgs.put("R", MissileImages[12]);
		imgs.put("RD", MissileImages[13]);
		imgs.put("D", MissileImages[14]);
		imgs.put("LD", MissileImages[15]);
		
	}
	public void draw(Graphics g){
		if(tankType){
			switch(miDir) {
			case L:
				g.drawImage(zimgs.get("L"),x,y,null);
				break;
			case LU:
				g.drawImage(zimgs.get("LU"),x,y,null);
				break;
			case U:
				g.drawImage(zimgs.get("U"),x,y,null);
				break;
			case RU:
				g.drawImage(zimgs.get("RU"),x,y,null);
				break;
			case R:
				g.drawImage(zimgs.get("R"),x,y,null);
				break;
			case RD:
				g.drawImage(zimgs.get("RD"),x,y,null);
				break;
			case D:
				g.drawImage(zimgs.get("D"),x,y,null);
				break;
			case LD:
				g.drawImage(zimgs.get("LD"),x,y,null);
				break;
			}
		}else{
			switch(miDir) {
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
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setMiDir(Direction miDir) {
		this.miDir = miDir;
	}
	public void setTankType(boolean tankType) {
		this.tankType = tankType;
	}
}