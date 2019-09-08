package TankWar;

import java.io.IOException;
import java.util.Properties;


public class PropertyMgr {
	static Properties props=new Properties();
	static{
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/TankWar.properties"));
		} catch (IOException e) {
			System.out.print("�����ļ���ʼ������!"+e.getMessage());
		}
	}
	//����ʹ��new�����ɹ���������
	private PropertyMgr(){};
	//���⹫���ķ���
	public static String getProperty(String key){
		return props.getProperty(key);
	}
}
