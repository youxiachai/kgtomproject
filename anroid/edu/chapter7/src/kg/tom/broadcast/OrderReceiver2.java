
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
public class OrderReceiver2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = getResultExtras(true);
		//解析前一个额BroadcastReceiver所存入的key为first的消息
		String first = bundle.getString("first");
		Toast.makeText(context, "第一个Broadcast存入的消息为:" + first, Toast.LENGTH_SHORT).show();
	}

}
