package TankWar;

import java.io.IOException;
import java.util.Properties;


public class PropertyMgr {
	static Properties props=new Properties();
	static{
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/TankWar.properties"));
		} catch (IOException e) {
			System.out.print("配置文件初始化错误!"+e.getMessage());
		}
	}
	//限制使用new来生成管理器对象
	private PropertyMgr(){};
	//对外公开的方法
	public static String getProperty(String key){
		return props.getProperty(key);
	}
}
