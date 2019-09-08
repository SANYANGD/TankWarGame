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
		//�½���ͼ���
		panelMain = new PanelMain();	
		panelMain.initPanel();
		panelMain.setBorder(new LineBorder(Color.BLUE));
		panelMain.setPreferredSize(new Dimension(800,600));		
		//�½�һ��������壬��ͼ�������ŵ���������У���ֱ������������ʾ��ˮƽ������ֻ������Ҫʱ��ʾ
		JScrollPane panel = new JScrollPane(panelMain,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelIcon = new PanelIcon();	
		panelIcon.initPanel();
		panelMain.setPanelIcon(panelIcon);
		panelIcon.setPanelMain(panelMain);
		
		setLayout(new BorderLayout());
		add(panelIcon,BorderLayout.WEST);
		add(panel);//	������������ӵ�����
		
		//��Ӳ˵�
		JMenuBar menuBar = new JMenuBar();//�½��˵���
		this.setJMenuBar(menuBar);//���˵�����ӵ�������
		
		JMenu fileMenu = new JMenu("File");//�½��˵�"File"
		fileMenu.setMnemonic('F');//����"File"�˵����Ƿ�
		menuBar.add(fileMenu);//���˵�"File"��ӵ��˵�����		
		
		JMenuItem newItem = new JMenuItem("new");//�½�"new"�˵���
		newItem.setAccelerator(KeyStroke.getKeyStroke('N'));//Ϊ�˵������"N"��ݼ�
		fileMenu.add(newItem);//��"new"�˵�����ӵ�"File"�˵���
		
		JMenuItem openItem = new JMenuItem("open");//�½�"open"�˵���
		//Ϊ�˵������Ctrl+O��ݼ�
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileMenu.add(openItem);//��"open"�˵�����ӵ�"File"�˵���
		
		JMenuItem saveItem = new JMenuItem("save");//�½�"open"�˵���
		//Ϊ�˵������Ctrl+S��ݼ�
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);//��"open"�˵�����ӵ�"File"�˵���
		
		saveItem.addActionListener(new actSave());
		
		this.setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//���ô���ɼ�
	}
	class actSave implements ActionListener{
		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			int state = fileDialog.showSaveDialog(null);
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	// ȡ���ļ�·����ʾ��tfDir�ı�����
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