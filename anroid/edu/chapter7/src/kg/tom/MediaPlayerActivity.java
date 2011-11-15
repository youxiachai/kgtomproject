
package kg.tom;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class MediaPlayerActivity extends Activity {
	private MediaPlayer mPlayer;
	private SoundPool mSoundPool;
	
	Button play_raw;
	Button play_sdcard;
	Button play_online;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplayer);
		
		play_raw = (Button) findViewById(R.id.play_raw);
		play_sdcard = (Button) findViewById(R.id.play_sdcard);
		play_online = (Button) findViewById(R.id.play_online);

		//播放应用的资源文件
		play_raw.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				playRaw();
			}
		});
		
		play_sdcard.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				playSdcard();
			}
		});
		
		play_online.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				playonLine();
			}
		});
	}
	
	//播放应用的资源文件方法
	public void playRaw(){
		
		if(mPlayer != null){
			mPlayer.release();
		}
		mPlayer = MediaPlayer.create(this, R.raw.air_fire);
		
		
		mPlayer.setOnErrorListener(new OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				//处理对应错误
				return false;
			}
		});
		//为MediaPlayer的播放完成事件绑定事件监听器
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				//处理播放完成,例如,播放下一首歌
			}
		});
		
		mPlayer.start();
	}
	
	//播放sdcard
	public void playSdcard(){
		if(mPlayer != null){
			mPlayer.release();
		}
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource("/mnt/sdcard/MP3/01 Numquam vincar.mp3");
			//准备播放
			mPlayer.prepare();
			//播放
			mPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mPlayer.setOnErrorListener(new OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				//处理对应错误
				return false;
			}
		});
		//为MediaPlayer的播放完成事件绑定事件监听器
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				//处理播放完成,例如,播放下一首歌
				Toast.makeText(MediaPlayerActivity.this, "完成播放", Toast.LENGTH_SHORT);
			}
		});
	}
	
	//播放网络的音频文件
	public void playonLine(){
		if(mPlayer != null){
			mPlayer.release();
		}
		Uri uri = Uri.parse("http://xxxxx.mp3");
		mPlayer = new MediaPlayer();
		try {
			mPlayer.setDataSource(this, uri);
			//准备播放
			mPlayer.prepare();
			//播放
			mPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mPlayer.setOnErrorListener(new OnErrorListener() {
			public boolean onError(MediaPlayer mp, int what, int extra) {
				//处理对应错误
				return false;
			}
		});
		//为MediaPlayer的播放完成事件绑定事件监听器
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				//处理播放完成,例如,播放下一首歌
				
				Toast.makeText(MediaPlayerActivity.this, "完成播放", Toast.LENGTH_SHORT);
			}
		});
	}
}
