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
		PanelIcon panel1 = new PanelIcon();
		panel1.initPanel();
		
		
		this.add(panel1,BorderLayout.WEST);
		JPanel panel2 = new JPanel();
		panel2.setBorder(new EtchedBorder());
		add(panel2,BorderLayout.CENTER);
		setVisible(true);
	}

}
