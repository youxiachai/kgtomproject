package kg.tom.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class OrderReceiver1 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(
				context,
				"接受到的intent action 为:" + intent.getAction() + "\n 接受到的消息内容: "
						+ intent.getStringExtra("msg"), Toast.LENGTH_SHORT)
				.show();

		// 创建一个Bundle对象,并存入数据
		Bundle bundle = new Bundle();
		bundle.putString("first", "第一个Broadcast存入的消息");
		// 将bundle 放入结果,这个方法只能在sendOrderBroadcast()方法中使用
		setResultExtras(bundle);
		// 取消广播
		//abortBroadcast();
	}

}
