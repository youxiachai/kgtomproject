package kg.tom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kg.tom.model.Mp3Info;
import kg.tom.service.Mp3playService;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Mp3CubeActivity extends ListActivity {
	
	ImageView player_controls;
	private boolean isPlay = false;
	//设置是否默认播放
	private boolean defaultPlay = true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigator_main);
		player_controls = (ImageView) findViewById(R.id.player_controls_play);
		player_controls.setOnClickListener(new PlayerControls());
		
		// 显示Mp3
		mediaScann();
		
	
	}
	
	class PlayerControls implements OnClickListener{
		public void onClick(View v) {
			if(!isPlay){
				Log.d("listener", "player--->play");
				if(!defaultPlay){
					playService(mp3Info);
				}else{
					playService(infos.get(0));
				}
			}else{
				Intent intent = new Intent();
				intent.setClass(Mp3CubeActivity.this, Mp3playService.class);
				intent.putExtra("playMsg", 0);	
				startService(intent);
				player_controls.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play_glow));
				isPlay=false;
			
			}
		}
	}
	
	//声明一个Mp3Info List对象
	private List<Mp3Info> infos = null;

	// 媒体文件扫描mp3
	public void mediaScann() {
		// 1,ArrayLis<Mp3Info> 对象
		infos = new ArrayList<Mp3Info>();
		// 2,创建ContentResolver对象
		ContentResolver contentResolver = getContentResolver();
		// 3,利用android系统提供的ContentProvider创建Uri对象
		Uri mediaUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		// 4,进行查询
		Cursor cursor = contentResolver.query(mediaUri, null, null, null, null);
		if (cursor == null) {
			// 如果查询为空报错
			Log.d("media", "query---->failed");
		} else if (!cursor.moveToFirst()) {
			// 设备中没有音频文件
			Log.d("media", "no media on the device");
		} else {
			int idColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);

			int dataColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.DATA);

			int displayNameColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.DISPLAY_NAME);

			int sizeColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.SIZE);

			int titleColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);

			int durationColumn = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.DURATION);

			int isMusicColum = cursor
					.getColumnIndex(android.provider.MediaStore.Audio.Media.IS_MUSIC);

			do {

				Integer id = cursor.getInt(idColumn);

				String data = cursor.getString(dataColumn);

				String displayName = cursor.getString(displayNameColumn);

				Integer size = cursor.getInt(sizeColumn);

				String title = cursor.getString(titleColumn);

				Integer duration = cursor.getInt(durationColumn);

				Integer isMusic = cursor.getInt(isMusicColum);

				// 将数据存放到list中
				Mp3Info mp3Info = new Mp3Info();
				mp3Info.setId(id);
				mp3Info.setData(data);
				mp3Info.setDisplay_name(displayName);
				mp3Info.setSize(size);
				mp3Info.setTitle(title);
				mp3Info.setDuration(duration);
				mp3Info.setIs_music(isMusic);
				infos.add(mp3Info);
			} while (cursor.moveToNext());
		} // end cursor
		mp3InfoListAdpater();
	}

	public void mp3InfoListAdpater() {
		// 1,生成一个list map对象
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 2,迭代添加
		for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
			Mp3Info mp3Info = (Mp3Info) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();

			if (mp3Info.getIs_music() != 0) {
				map.put("mp3_id", String.valueOf(mp3Info.getId()+":"));
				map.put("mp3_name", mp3Info.getTitle());
			}

			list.add(map);

		}
		// 添加数据到Adapterview中
		SimpleAdapter mp3list = new SimpleAdapter(this, list,
				R.layout.item_list, new String[] { "mp3_id", "mp3_name" },
				new int[] { R.id.mp3_id, R.id.mp3_name });
		setListAdapter(mp3list);
	}
	Mp3Info mp3Info;
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(infos != null){
			mp3Info = infos.get(position);
			playService(mp3Info);
		}
	}
	//用Service播放MP3文件
	public void playService(Mp3Info mp3Info){
		Intent intent = new Intent();
		intent.setClass(Mp3CubeActivity.this, Mp3playService.class);
		//将播放状态传给Service
		intent.putExtra("playMsg", 1);
		//把MP3信息对象传给Service
		intent.putExtra("mp3Info", mp3Info);
		startService(intent);
		//更改控制栏上的图标
		player_controls.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause));
		//将播放设置为真
		isPlay=true;
		defaultPlay = false;
	}
}