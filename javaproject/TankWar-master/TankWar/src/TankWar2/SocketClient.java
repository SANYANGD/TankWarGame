package TankWar2;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;



public class SocketClient {
	TankClient tc;

	private int udpPort;
	
	String IP; // server IP

	DatagramSocket ds = null;
	
	
	public SocketClient(TankClient tc) {
		this.tc = tc;
	}
	
	public void connect(String IP, int tcpPort) 
	{
		this.IP = IP;
		
		try {
				ds = new DatagramSocket(udpPort);  // ������udp�˿ڷ�����Ϣ
			} 
		catch (SocketException e) {
				e.printStackTrace();
			}
		Socket s = null;
		try {
			s = new Socket(IP, tcpPort);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeInt(udpPort);   //  ��1��      ��һ�η�����Ϣ�����ͱ�����udp�˿ں�
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int id = dis.readInt(); // ��1��      ��һ�ν��գ�����id��
			tc.mytank.id = id; //�����Լ���̹��id

		/*	if (id % 2 == 0)
				tc.mytank.setGood(false);
			else
				tc.mytank.setGood(true);
*/
			tc.mytank.setGood(true); 
			System.out.println("Connected to server! and server give me a ID:"
					+ id);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
					s = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	/**
	 *      ����̹����Ϣ
	 * */	TankNewMsg msg = new TankNewMsg(tc.mytank);
		send(msg);

		new Thread(new UDPRecvThread()).start();// udp �����߳̿���
	}
	public void send(Msg msg) {
		msg.send(ds, IP, TankServer.UDP_PORT);
	}
	private class UDPRecvThread implements Runnable {

		byte[] buf = new byte[1024];

		public void run() {

			while (ds != null)  //���������udp����û�ر�
			{
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				try 
				{
					ds.receive(dp);  // �������������ݰ�
					parse(dp);          //�������ݰ�
					System.out.println("a packet received from server!");
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void parse(DatagramPacket dp) 
		{
			ByteArrayInputStream bais = new ByteArrayInputStream(buf, 0, dp
					.getLength());   //�������װ���ֽ����������
			DataInputStream dis = new DataInputStream(bais);//�������װ�ֽ����������
			int msgType = 0;  //  ��Ϣ����   ��  �¼�̹����Ϣ/̹���ƶ���Ϣ/�ӵ�������Ϣ
			try {
				msgType = dis.readInt();       //��2�� ������Ϣ����
			} catch (IOException e) {
				e.printStackTrace();
			}
			Msg msg = null;
			switch (msgType) {
			case Msg.TANK_NEW_MSG:
				msg = new TankNewMsg(SocketClient.this.tc);
				msg.parse(dis);      //����
				break;
			case Msg.TANK_MOVE_MSG:
				msg = new TankMoveMsg(SocketClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_NEW_MSG:
				msg = new MissileNewMsg(SocketClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.TANK_DEAD_MSG:
				msg = new TankDeadMsg(SocketClient.this.tc);
				msg.parse(dis);
				break;
			case Msg.MISSILE_DEAD_MSG:
				msg = new MissileDeadMsg(SocketClient.this.tc);
				msg.parse(dis);
				break;
			}

		}

	}
	
	/**
	 * ȡ��UDP�˿�(�ͻ��˽���������)
	 * @return
	 */
	public int getUdpPort() {
		return udpPort;
	}
	
	/**
	 * �趨UDP�˿�(�ͻ��˽���������)
	 * @param udpPort
	 */
	public void setUdpPort(int udpPort) {
		this.udpPort = udpPort;
	}

	
}
