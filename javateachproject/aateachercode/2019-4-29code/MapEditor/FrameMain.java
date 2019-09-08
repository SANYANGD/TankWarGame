package MapEditor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
public class FrameMain extends JFrame{

	public void initFrame(){
		this.setTitle("µØÍ¼±à¼­Æ÷");
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		PanelIcon panelIcon = new PanelIcon();
		panelIcon.initPanel();		
		this.add(panelIcon,BorderLayout.WEST);
		
		
		PanelMain panelMain = new PanelMain();
		panelMain.initPanel();		
		panelMain.setPanelIcon(panelIcon);
		add(panelMain,BorderLayout.CENTER);
		
		setVisible(true);
	}

}
