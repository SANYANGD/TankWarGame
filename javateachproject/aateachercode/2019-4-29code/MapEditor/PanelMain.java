package MapEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
public class PanelMain extends JPanel{

	private PanelIcon panelIcon; 
	private List waterList = new ArrayList();
	private List grassList = new ArrayList();
	public void initPanel(){
		this.setBorder(new EtchedBorder());
		this.addMouseListener(new MyClick());
	}
	public void setPanelIcon(PanelIcon panelIcon){
		this.panelIcon = panelIcon;
	}
	private class MyClick extends MouseAdapter{
		public void mouseClicked(MouseEvent obj){
			switch(panelIcon.getIndex()){
			case 0:
				waterList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
				break;
			case 1:
				grassList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
				break;
			}			
			repaint();
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		for(int i=0;i<waterList.size();i++){
			Point point = (Point)waterList.get(i);
			ImgSource.getInstance().drawWater(g,point.x,point.y);
		}
		for(int i=0;i<grassList.size();i++){
			Point point = (Point)grassList.get(i);
			ImgSource.getInstance().drawGrass(g,point.x,point.y);
		}
		
		//ImgSource.getInstance().drawGrass(g,iconRect[1][0],iconRect[1][1]);
		//ImgSource.getInstance().drawBrick(g,iconRect[2][0],iconRect[2][1]);
		//ImgSource.getInstance().drawIron(g,iconRect[3][0],iconRect[3][1]);
		
	}
}
