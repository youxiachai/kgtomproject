package kg.tom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class StartServiceActivity extends Activity {

	Button start;
	Button stop;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		// 获取界面的两个Button
		start = (Button) findViewById(R.id.start_service);
		stop = (Button) findViewById(R.id.stop_service);

		// 创建启动的Service的Intent
		final Intent intent = new Intent()
				.setAction("kg.tom.service.PALY_SERVICE");

		start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 启动指定的Servic
				startService(intent);
			}
		});

		stop.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 停止指定的Service
				stopService(intent);
			}
		});

	}

}
