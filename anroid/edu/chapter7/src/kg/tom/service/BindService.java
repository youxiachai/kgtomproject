package kg.tom.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class BindService extends Service {

	private int count;
	private boolean quit;

	// 定义onBinder方法所返回的对象
	private MyBinder binder = new MyBinder();

	public class MyBinder extends Binder {
		public int getCount() {
			// 获取service状态的count
			return count;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// 返回我们的binder对象
		return binder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("chapter", "oncreate");
		// 启动一条线程,动态修改count的值
		new Thread() {
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}

			};
		}.start();
	}

	// 断开连接时回调方法
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return true;
	}

	// Service关闭之前回调
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		this.quit = true;
	}
}
