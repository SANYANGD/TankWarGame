package MapEditor;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.event.MouseListener;

public class Panellcon extends JPanel{
	private int[][] iconRect= {{10,50},{54,50},{10,90},{54,90},{10,130},{54,130},{10,170},{54,170}};
    private int selectIndex=-1;
	public void initPanel() {
		setBorder(new EtchedBorder());
		add(new JLabel("elements"));
		this.addMouseListener(new MyClick());
	}
	public int getIndex() {
		return selectIndex;
	}
    public void paint(Graphics g) {
    	super.paint(g);
    	ImgSource.getInstance().drawWater(g,iconRect[0][0],iconRect[0][1]);
    	ImgSource.getInstance().drawGrass(g,iconRect[1][0],iconRect[1][1]);
    	ImgSource.getInstance().drawBrick(g,iconRect[2][0],iconRect[2][1]);
    	ImgSource.getInstance().drawIron(g,iconRect[3][0],iconRect[3][1]);
    	ImgSource.getInstance().drawPtankhome(g,iconRect[4][0],iconRect[4][1]);
    	ImgSource.getInstance().drawAtankhome(g,iconRect[5][0],iconRect[5][1]);
    	ImgSource.getInstance().drawPtank(g,iconRect[6][0],iconRect[6][1]);
    	ImgSource.getInstance().drawAtank(g,iconRect[7][0],iconRect[7][1]);
    	if(selectIndex>=0)
    	{
    		g.drawRect(iconRect[selectIndex][0],iconRect[selectIndex][1],34,34);
    	}
    	/*else if(selectIndex==1){
    		g.drawRect(54,50,35,35);
    	}*/
    }
	private class MyClick extends MouseAdapter {
		public void mouseClicked(MouseEvent obj) {
			for(int i=0;i<iconRect.length;i++) {
				if(obj.getX()>iconRect[i][0]&&obj.getX()<iconRect[i][0]+34&&obj.getY()>iconRect[i][1]&&obj.getY()<iconRect[i][1]+34) {
					selectIndex=i;
				}
			}
			repaint();
		}
	}
	/*private class MyClick implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent obj) {
			for(int i=0;i<iconRect.length;i++) {
				if(obj.getX()>iconRect[i][0]&&obj.getX()<iconRect[i][0]+34&&obj.getY()>iconRect[i][1]&&obj.getY()<iconRect[i][1]+34) {
					selectIndex=i;
				}
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	}*/
}
