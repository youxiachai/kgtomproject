
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
public class SortedBroadcastActivity extends Activity {
	Button send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast);
		send = (Button) findViewById(R.id.send_broadcast);
		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 创建Intent对象
				Intent intent = new Intent().setAction(
						"kg.tom.action.ORDER_BROADCAST").putExtra("msg",
						"发送消息------->");
				// 发送有序广播
				sendOrderedBroadcast(intent, null);
			}
		});
	}
}
