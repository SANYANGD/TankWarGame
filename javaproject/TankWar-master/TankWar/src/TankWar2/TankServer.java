package TankWar2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;





public class TankServer {

	private static int ID = 100; //̹�˵� id
	ServerSocket ss=null;
	DatagramSocket ds=null;
	byte[] buf=new byte[1024];
	public static final int TCP_PORT=8888;
	public static final int UDP_PORT = 6666;//����˼��� 6666 �˿ں�
	
	ArrayList <Client> clients =new ArrayList<Client>();
	
	
	public void start()
	{
		new Thread(new UdpThread()).start();;
		
		Socket s=null;
		try 
		{
			ss=new ServerSocket(TCP_PORT);
			while(true)
			{
				s=ss.accept();
				
				
				DataInputStream dis = new DataInputStream(s.getInputStream());
				
				String IP = s.getInetAddress().getHostAddress();
				
				int udpPort = dis.readInt();//��1�� ��һ�ν���  
				
				Client c = new Client(IP, udpPort); // �����µĿͻ��˶���
				clients.add(c);
				
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(ID++);//(1)  ��һ�η��� ����̹��ID
			
			}
				
		} 
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(s != null) {
				try {
					s.close();//TCP �������֮��  �Ϳ��Թر���
					s = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
		
	}
	
	private class UdpThread	implements Runnable
	{
		byte[] buf = new byte[1024];
		public void run()
		{
			DatagramSocket ds = null;
			try {
				
				ds = new DatagramSocket(UDP_PORT);
				
			}
			catch (SocketException e) {
				e.printStackTrace();
			}
			System.out.println("UDP thread started at port :" + UDP_PORT);
			
			while(ds != null)
			{
				
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(dp);
					for(int i=0; i<clients.size(); i++)//���ͻ����б����ÿһ���ͻ��˷�����Ϣ
					{
						Client c = clients.get(i);
						dp.setSocketAddress(new InetSocketAddress(c.ip, c.port));
						//  ����Ҫ�������ݱ�������Զ�������� SocketAddress��ͨ��Ϊ IP ��ַ + �˿ںţ���
						ds.send(dp);
					}
					System.out.println("a packet received!");
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		
		}
	}
	public static void main(String[] args) {
		new TankServer().start();
	}
	private class Client
	{
		String ip;
		int port;
		public Client(String ip,int port)
		{
			this.ip=ip;
			this.port=port;
		}
	}
			
}
