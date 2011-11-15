package kg.tom;

import android.app.ListActivity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Chapter7Activity extends ListActivity {
	
	//定义例子中的运行类
	final static int START_STOP_SERVICE = 0;
	final static int BIND_SERVICE = 1;
	final static int SEND_BROADCAST = 2;
	final static int ORDER_BROADCAST = 3;
	final static int MEDIAPLAYER = 4;
	final static int SOUNDPOOL = 5;
	final static int VIDEOVIEW = 6;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] chapter_seven = getResources().getStringArray(R.array.chapter7);
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.item_list, chapter_seven));
    }
 
    
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	// TODO Auto-generated method stub
    	super.onListItemClick(l, v, position, id);
    	
    	  
    	  Log.d("chapter", String.valueOf(position));
    	  switch (position) {
		case START_STOP_SERVICE:
			Intent intent = new Intent().setClass(this, StartServiceActivity.class);
			startActivity(intent);
			break;
		case BIND_SERVICE:
			Intent bind_service = new Intent().setClass(this, BindServiceActivity.class);
			startActivity(bind_service);
			break;
		case SEND_BROADCAST:
			Intent send_broadcast = new Intent().setClass(this, BroadcastActivity.class);
			startActivity(send_broadcast);
			break;
		case ORDER_BROADCAST:
			Intent order_broadcast = new Intent().setClass(this, SortedBroadcastActivity.class);
			startActivity(order_broadcast);
			break;
		case MEDIAPLAYER:
			Intent mediaplayer = new Intent().setClass(this, MediaPlayerActivity.class);
			startActivity(mediaplayer);
			break;
		case SOUNDPOOL:
			Intent soundPool = new Intent().setClass(this, SoundPoolActivity.class);
			startActivity(soundPool);
			break;
		case VIDEOVIEW:
			Intent videoPlayer = new Intent().setClass(this, VideoPlayerActivity.class);
			startActivity(videoPlayer);
			break;
		default:
			break;
		}

    }
}