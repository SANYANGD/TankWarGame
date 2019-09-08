package MapEditor;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
public class PanelMain extends JPanel{
    private Panellcon panelIcon;
    private List waterList =new ArrayList();
    private List grassList =new ArrayList();
    private List brickList =new ArrayList();
    private List ironList =new ArrayList();
    private List ptankhomeList =new ArrayList();
    private List atankhomeList =new ArrayList();
    private List ptankList =new ArrayList();
    private List atankList =new ArrayList();
    private JLabel  label;
	public void initPanel() {
		this.setBorder(new EtchedBorder());
		this.addMouseListener(new MyClick());
	}
	public void setPanelIcon(Panellcon panelIcon ) {
		this.panelIcon=panelIcon;
	}
	public PanelMain() {
		label=new JLabel("代春洋2018141493004");
		label.setFont(new Font("Serif", Font.BOLD, 30));
		label.setHorizontalAlignment(JLabel.CENTER);
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		label.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	private class MyClick extends MouseAdapter {
		public void mouseClicked(MouseEvent obj) {
		    switch(panelIcon.getIndex()) {
		    case 0:
		    	waterList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 1:
		    	grassList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 2:
		    	brickList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 3:
		    	ironList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 4:
		    	ptankhomeList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 5:
		    	atankhomeList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 6:
		    	ptankList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    case 7:
		    	atankList.add(new Point(obj.getX()/34*34,obj.getY()/34*34));
		    	break;
		    }
			repaint();
		}
	}
	public void paint(Graphics g) {
    	super.paint(g);
    	for(int i=0;i<waterList.size();i++) {
    		Point point=(Point)waterList.get(i);
    		ImgSource.getInstance().drawWater(g,point.x,point.y);
    	}
    	for(int i=0;i<grassList.size();i++) {
    		Point point=(Point)grassList.get(i);
    		ImgSource.getInstance().drawGrass(g,point.x,point.y);
    	}
    	for(int i=0;i<brickList.size();i++) {
    		Point point=(Point)brickList.get(i);
    		ImgSource.getInstance().drawBrick(g,point.x,point.y);
    	}
    	for(int i=0;i<ironList.size();i++) {
    		Point point=(Point)ironList.get(i);
    		ImgSource.getInstance().drawIron(g,point.x,point.y);
    	}
    	for(int i=0;i<ptankhomeList.size();i++) {
    		Point point=(Point)ptankhomeList.get(i);
    		ImgSource.getInstance().drawPtankhome(g,point.x,point.y);
    	}
    	for(int i=0;i<atankhomeList.size();i++) {
    		Point point=(Point)atankhomeList.get(i);
    		ImgSource.getInstance().drawAtankhome(g,point.x,point.y);
    	}
    	for(int i=0;i<ptankList.size();i++) {
    		Point point=(Point)ptankList.get(i);
    		ImgSource.getInstance().drawPtank(g,point.x,point.y);
    	}
    	for(int i=0;i<atankList.size();i++) {
    		Point point=(Point)atankList.get(i);
    		ImgSource.getInstance().drawAtank(g,point.x,point.y);
    	}
    	//ImgSource.getInstance().drawWater(g,10,50);
    	//ImgSource.getInstance().drawGrass(g,54,50);
    	/*if(selectIndex>=0){
    		g.drawRect(iconRect[selectIndex][0],iconRect[selectIndex][1],34,34);
    	}
    	else if(selectIndex==1){
    		g.drawRect(54,50,35,35);
    	}*/
    }
}
