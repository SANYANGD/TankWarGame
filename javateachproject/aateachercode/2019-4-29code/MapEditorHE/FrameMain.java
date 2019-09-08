package MapEditor;
import javax.swing.JFrame;
import java.awt.BorderLayout;
public class FrameMain extends JFrame{
      public void initFrame() {
    	  this.setTitle("代春洋2018141493004");
    	  this.setSize(1000, 600);
    	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	  this.setLayout(new BorderLayout());
    	  Panellcon panelIcon=new Panellcon();
    	  panelIcon.initPanel(); 
    	  this.add(panelIcon,BorderLayout.WEST);
    	  
    	  PanelMain panelMain=new PanelMain();
    	  panelMain.initPanel();
    	  panelMain.setPanelIcon(panelIcon);
    	  add(panelMain,BorderLayout.CENTER);
    	  
    	  setVisible(true);
      }
}
