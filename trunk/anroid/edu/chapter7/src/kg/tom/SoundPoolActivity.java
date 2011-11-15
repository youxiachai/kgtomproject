package kg.tom;

import java.util.HashMap;

import javax.xml.transform.Source;

import android.app.Activity;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class SoundPoolActivity extends Activity implements OnClickListener {

	Button airfire, explosion, gameover;
	// 定义一个soundpool
	SoundPool soundPool;
	// 定义一个hashmap 管理音频流
	HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.soundpool);
		airfire = (Button) findViewById(R.id.sound_airfire);
		explosion = (Button) findViewById(R.id.sound_explosion);
		gameover = (Button) findViewById(R.id.sound_gameover);

		// 注释一:设置最多可容纳10个音频流,音频品质为5
		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		
		//注释二
		// load方法加载指定音频文件
		// 并返回所加载的音频ID,此处使用
		// HashMap来管理这些音频流
		soundMap.put(1, soundPool.load(this, R.raw.air_fire, 1));
		soundMap.put(2, soundPool.load(this, R.raw.explosion, 1));
		soundMap.put(3, soundPool.load(this, R.raw.gameover, 1));
		airfire.setOnClickListener(this);
		explosion.setOnClickListener(this);
		gameover.setOnClickListener(this);

	}

	public void onClick(View v) {

		// 判断哪个按钮被单击
		switch (v.getId()) {
		case R.id.sound_airfire:
			//注释三
			soundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
			break;
		case R.id.sound_explosion:
			soundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			break;
		case R.id.sound_gameover:
			soundPool.play(soundMap.get(3), 1, 1, 0, 0, 1);
			break;
		default:
			break;
		}

	}
}
