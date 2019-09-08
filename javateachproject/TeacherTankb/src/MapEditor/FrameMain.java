package MapEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import Game.ImgSource;
public class FrameMain extends JFrame{
	private PanelMain panelMain;
	private PanelIcon panelIcon;
	public void initFrame() {
		//新建地图面板
		panelMain = new PanelMain();	
		panelMain.initPanel();
		panelMain.setBorder(new LineBorder(Color.BLUE));
		panelMain.setPreferredSize(new Dimension(800,600));		
		//新建一个滚动面板，地图面板区域放到滚动面板中，垂直滚动条总是显示，水平滚动条只有在需要时显示
		JScrollPane panel = new JScrollPane(panelMain,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelIcon = new PanelIcon();	
		panelIcon.initPanel();
		panelMain.setPanelIcon(panelIcon);
		panelIcon.setPanelMain(panelMain);
		
		setLayout(new BorderLayout());
		add(panelIcon,BorderLayout.WEST);
		add(panel);//	将滚动区域添加到窗体
		
		//添加菜单
		JMenuBar menuBar = new JMenuBar();//新建菜单条
		this.setJMenuBar(menuBar);//将菜单条添加到窗体上
		
		JMenu fileMenu = new JMenu("File");//新建菜单"File"
		fileMenu.setMnemonic('F');//设置"File"菜单助记符
		menuBar.add(fileMenu);//将菜单"File"添加到菜单条中		
		
		JMenuItem newItem = new JMenuItem("new");//新建"new"菜单项
		newItem.setAccelerator(KeyStroke.getKeyStroke('N'));//为菜单项添加"N"快捷键
		fileMenu.add(newItem);//将"new"菜单项添加到"File"菜单中
		
		JMenuItem openItem = new JMenuItem("open");//新建"open"菜单项
		//为菜单项添加Ctrl+O快捷键
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileMenu.add(openItem);//将"open"菜单项添加到"File"菜单中
		
		JMenuItem saveItem = new JMenuItem("save");//新建"open"菜单项
		//为菜单项添加Ctrl+S快捷键
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);//将"open"菜单项添加到"File"菜单中
		
		saveItem.addActionListener(new actSave());
		
		this.setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//设置窗体可见
	}
	class actSave implements ActionListener{
		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			int state = fileDialog.showSaveDialog(null);
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	// 取得文件路径显示在tfDir文本框中
	        	try {
					panelMain.saveMap(fileDialog.getSelectedFile().getAbsoluteFile());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());
	        }
		}
	}
}