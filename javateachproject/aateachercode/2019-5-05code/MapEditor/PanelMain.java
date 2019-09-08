package MapEditor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;

import Game.ImgSource;

public class PanelMain extends JPanel{

	private PanelIcon panelIcon;
	private List<MapElement> elementList = new ArrayList<MapElement>();
	
	public void initPanel() {
		this.addMouseListener(new MouseClick());
		this.addKeyListener(new KeyClick());
	}
	public void setPanelIcon(PanelIcon panelIcon) {
		this.panelIcon = panelIcon;
	}
	/**
	 * 重写绘制组件方法
	 */	
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0;i<elementList.size();i++) {
			elementList.get(i).draw(g);
		}			
	}
	private class KeyClick extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
	
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DELETE:
				System.out.println("按下了delete键");
			}
		}			
	}
	private class MouseClick extends MouseAdapter {	
				
		public void mouseClicked(MouseEvent obj) {
			//JPanel panel = (JPanel)obj.getSource();
			//panel.requestFocus();//每次点击时，PanelMain获得输入焦点，以监听键盘事件。
			int index = -1;
			for(int i=0;i<elementList.size();i++) {
				if(elementList.get(i).pointSelect(obj.getX(), obj.getY())) {
					index = i;
				}
			}
			MapElement curElement;
			curElement = panelIcon.getCurElement();
			if(index != -1) {				
				if(curElement instanceof MapElementSpade) {
					elementList.remove(index);				
				}
			}else {
				if(curElement != null) {
					if(!(curElement instanceof MapElementSpade)){
						elementList.add(curElement.CloneElement(obj.getX()/curElement.width*curElement.width, 
								obj.getY()/curElement.width*curElement.width));
					}
				}				
			}
			
			repaint();//重新绘制界面，不重新绘制界面无法看到图形
		}
	}
	public void saveMap(File file) throws FileNotFoundException {
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<elementList.size();i++) {
			String type = elementList.get(i).getType();	
			String value = map.get(type);
			if(value != null) {
				value = value + elementList.get(i);
			}else {
				value = elementList.get(i).toString();
			}
				
			map.put(type, value);
		}
	
		OutputStream out = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pw = new PrintWriter(bw,true);
		
		Set<String> sets = map.keySet();//返回键的set集合
		for(String str: sets) {//遍历每一个键值
			System.out.println(map.get(str));//返回键值对应的value
			pw.println(str + "="+map.get(str));
		}

		pw.close();
	}
}
