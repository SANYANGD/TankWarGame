package MapEditor;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class PanelIcon extends JPanel {
	private int[][] iconRect = {{10,50},{54,50},{10,90},{54,90}};
	private int selectIndex = -1;
	public void initPanel(){
		setBorder(new EtchedBorder());
		add(new JLabel("¸÷ÖÖµØÍ¼Í¼Àý"));
		this.addMouseListener(new MyClick());
	}
	public int getIndex(){
		return selectIndex;
	}
	public void paint(Graphics g){
		super.paint(g);
		ImgSource.getInstance().drawWater(g,iconRect[0][0],iconRect[0][1]);
		ImgSource.getInstance().drawGrass(g,iconRect[1][0],iconRect[1][1]);
		ImgSource.getInstance().drawBrick(g,iconRect[2][0],iconRect[2][1]);
		ImgSource.getInstance().drawIron(g,iconRect[3][0],iconRect[3][1]);
		if(selectIndex >=0){			
			g.drawRect(iconRect[selectIndex][0],
					iconRect[selectIndex][1],34,34);
		}

	}
	private class MyClick extends MouseAdapter{
		public void mouseClicked(MouseEvent obj){
			for(int i=0;i<iconRect.length;i++){
				if(obj.getX()>iconRect[i][0] && 
						obj.getX()<iconRect[i][0]+34 &&
						obj.getY()>iconRect[i][1] && 
						obj.getY()<iconRect[i][1]+34){				
					selectIndex = i;				
				}
			}		
			repaint();
		}
	}



}
