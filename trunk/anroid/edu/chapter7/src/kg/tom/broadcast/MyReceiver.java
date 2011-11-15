package kg.tom.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(
				context,
				"接受到的intent action 为:" + intent.getAction() + "\n 接受到的消息内容: "
						+ intent.getStringExtra("msg"), Toast.LENGTH_SHORT)
				.show();
		
		//创建一个Bundle对象,并存入数据
		
	}

}
