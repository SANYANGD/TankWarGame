package MapEditor;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class PanelIcon extends JPanel {
	private int selectIndex = -1;
	public void initPanel(){
		setBorder(new EtchedBorder());
		add(new JLabel("¸÷ÖÖµØÍ¼Í¼Àý"));
		this.addMouseListener(new MyClick());
	}
	
	public void paint(Graphics g){
		super.paint(g);
		ImgSource.getInstance().drawWater(g,10,50);
		ImgSource.getInstance().drawGrass(g,54,50);
		if(selectIndex ==0){			
			g.drawRect(10,50,35,35);
		}else if(selectIndex ==1){			
			g.drawRect(54,50,35,35);
		}
	}
	private class MyClick extends MouseAdapter{
		public void mouseClicked(MouseEvent obj){
			if(obj.getX()>10 && obj.getX()<43 &&
					obj.getY()>50 && obj.getY()<83){				
				selectIndex = 0;				
			}else if(obj.getX()>54 && obj.getX()<87 &&
					obj.getY()>50 && obj.getY()<83){				
				selectIndex = 1;				
			}
			repaint();
		}
	}



}
