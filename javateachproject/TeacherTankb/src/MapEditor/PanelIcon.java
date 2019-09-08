package MapEditor;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PanelIcon extends JPanel{
	private int[][] iconRect = {{10,50},{10,90},{54,90},
			{10,130},{54,130},{10,170},{54,170},{10,210},{54,210}};
	private List<MapElement> iconList = new ArrayList<MapElement>();
	private int currentIndex=-1;
	PanelMain panelMain;
	public void initPanel() {
		Label label = new Label("   Elements   ");
		setBorder(new EtchedBorder());
		add(label);		
		this.addMouseListener(new MouseClick());
		iconList.add(new MapElementSpade(iconRect[0][0],iconRect[0][1]));
		iconList.add(new MapElementWater(iconRect[1][0],iconRect[1][1]));
		iconList.add(new MapElementGrass(iconRect[2][0],iconRect[2][1]));
		iconList.add(new MapElementBrick(iconRect[3][0]+8,iconRect[3][1]+8));
		iconList.add(new MapElementIron(iconRect[4][0]+8,iconRect[4][1]+8));
		iconList.add(new MapElementPlayerTank(iconRect[5][0],iconRect[5][1]));
		iconList.add(new MapElementAutoTank(iconRect[6][0],iconRect[6][1]));
		iconList.add(new MapElementBase(iconRect[7][0],iconRect[7][1]));
	}
	public void setPanelMain(PanelMain panel) {
		this.panelMain = panel;
	}
	public MapElement getCurElement() {
		if(currentIndex>=0 && currentIndex < iconList.size()) {
			return iconList.get(currentIndex);
		}else {
			return null;
		}
	}
	/**
	 * 重写绘制组件方法
	 */
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0;i<iconList.size();i++) {
			iconList.get(i).draw(g); 
			if(currentIndex == i) { //画选择框
				g.drawRect(iconRect[currentIndex][0], iconRect[currentIndex][1], 34, 34);	
			}
		}		
	}
	private class MouseClick extends MouseAdapter {		
		public void mouseClicked(MouseEvent obj) {
			for(int i=0;i<iconList.size();i++) {
				if(iconList.get(i).pointSelect(obj.getX(), obj.getY()) ) {
					currentIndex = i;				
					Toolkit tk = Toolkit.getDefaultToolkit();					
					Cursor cursor = tk.createCustomCursor(iconList.get(i).getCursor(), new Point(3, 30), "norm");
					panelMain.setCursor(cursor);
				}
			}
			repaint();//重新绘制界面，不重新绘制界面无法看到图形
		}
	}
}
