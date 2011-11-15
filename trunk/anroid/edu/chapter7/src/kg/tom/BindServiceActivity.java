
package kg.tom;


import kg.tom.service.BindService.MyBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class BindServiceActivity extends Activity {
	
	Button bindService;
	Button unbindService;
	Button serviceStatus;
	
	//保持所启动的Service的IBinder对象
	MyBinder binder;
	
	//定义个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection() {
		//断开连接时回调该方法
		public void onServiceDisconnected(ComponentName name) {
			
		}
		//连接成功回调该方法
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (MyBinder) service;
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.bindservice);
		bindService = (Button) findViewById(R.id.bindservice);
		unbindService = (Button) findViewById(R.id.unbindservice);
		serviceStatus = (Button) findViewById(R.id.servicestatus);
		//创建启动的Service的Intent
		final Intent intent = new Intent().setAction("kg.tom.service.BIND_SERVICE");
		bindService.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//绑定指定的Service
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
		
		unbindService.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//解除绑定
				unbindService(conn);
			}
		});
		
		serviceStatus.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.d("chapter", "ok----");
				// 获取并显示service的count值
				Toast.makeText(getApplicationContext(), "Service的count值为:" + binder.getCount(), Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
