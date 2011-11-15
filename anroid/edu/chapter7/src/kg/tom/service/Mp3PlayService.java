
package kg.tom.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class Mp3PlayService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Service创建时回调该方法
		@Override
		public void onCreate() {
			super.onCreate();
			System.out.println("service 创建了");
			
		}
	
	//Service被启动时回调该方法
		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			System.out.println("service 现在启动");
			return super.onStartCommand(intent, flags, startId);
		}
		//Service被关闭之前回调
		@Override
		public void onDestroy() {
			super.onDestroy();
			System.out.println("Service 现在销毁");
		}
		//Service绑定的所有客户端断开连接时回调该方法
		@Override
		public boolean onUnbind(Intent intent) {
			System.out.println("Service 断开所有客户端连接");
			return super.onUnbind(intent);
		}

}
