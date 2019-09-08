package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * 爆炸效果模拟类
 * @author qbg
 *
 */
public class Explode {
	
	private boolean live=true;
	private int x,y;
	private int step=0;
	private TankClient tc;
	private boolean init=false;
	/*
	 * Toolkit调用系统本地API实现硬盘资源加载到内存
	 * 此处加载的为图片
	 * 用途可参考相应文档或马士兵TankWar教程
	 */
	private static Toolkit tk=Toolkit.getDefaultToolkit();
	private static Image[] images={
			tk.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif"))
	};
	/**
	 * 爆炸构造函数
	 * @param x 初始X位置
	 * @param y 初始Y位置
	 * @param tc 大管家--TankClient
	 */
	public Explode(int x,int y,TankClient tc){
		this.x=x;
		this.y=y;
		this.tc=tc;
	}
	/**
	 * 爆炸绘画函数
	 * @param g 画笔
	 */
	public void draw(Graphics g){
		if(!init){
			for(int i=0;i<images.length;i++){
				g.drawImage(images[i],-100,-100,null);
			}
			init=true;
		}
		if(!live){
			tc.explodes.remove(this);
			return;
		}
		if( step >= images.length){
			live=false;
			step=0;
			return;
		}
		g.drawImage(images[step], x, y, null);
		step ++;
	}
}
