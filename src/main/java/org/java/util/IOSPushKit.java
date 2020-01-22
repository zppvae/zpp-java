package org.java.util;

import javapns.devices.Device;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;

public class IOSPushKit {

	public static void main(String[] args) throws Exception 
	{
	        try
	        {
	            //从客户端获取的deviceToken，在此为了测试简单，写固定的一个测试设备标识。
	           String deviceToken = "7d657a8ed9da3f27637588815396418ad249c6e5ebeae98b1f7174bee387fb92";
	            System.out.println("Push Start deviceToken:" + deviceToken);
	            //定义消息模式
	            PushNotificationPayload payLoad = new PushNotificationPayload();
	            payLoad.addAlert("this is test!");
	            payLoad.addBadge(1);//消息推送标记数，小红圈中显示的数字。
	            payLoad.addSound("default");
	            //注册deviceToken
	            PushNotificationManager pushManager = new PushNotificationManager();
	            pushManager.addDevice("iPhone", deviceToken);
	            //连接APNS
	            String host = "gateway.sandbox.push.apple.com";
	            //Strings host = "gateway.push.apple.com";
	            int port = 2195;
	            String certificatePath = "d:/voippush.p12";//前面生成的用于JAVA后台连接APNS服务的*.p12文件位置
	            String certificatePassword = "123456";//p12文件密码。
	            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword,false));
	            //发送推送
	            Device client = pushManager.getDevice("iPhone");
	            System.out.println("推送消息: " + client.getToken()+"\n"+payLoad.toString() +" ");
	            pushManager.sendNotification(client, payLoad);
	            //停止连接APNS
	            pushManager.stopConnection();
	            //删除deviceToken
	            pushManager.removeDevice("iPhone");
	            System.out.println("Push End");
	        }
	        catch (Exception ex)
	        {
	            ex.printStackTrace();
	        }
	}

}
