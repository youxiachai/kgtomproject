package kg.tom.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;

import kg.tom.model.Mp3Info;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-11-9
 * 
 */
public class Mp3playService extends Service {

	// 创建播放器
	private MediaPlayer mediaMp3Play = null;

	// 状态标识
	private boolean isPlay = false;
	private boolean isPause = false;
	private boolean isStop = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	Mp3Info mp3infoService = null;
	Mp3Info mp3Compare;

	public class PlayMsg {
		public static final int PLAY_MSG = 1;
		public static final int PAUSE_MSG = 0;
		public static final int STOP_MSG = -1;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 接受Mp3信息
		int playMsg = intent.getIntExtra("playMsg", 0);
		Mp3Info mp3info = (Mp3Info) intent.getSerializableExtra("mp3Info");
		Log.d("listener", "playMsg--->" + String.valueOf(playMsg));
		if (mp3info == null) {
			mp3info = mp3infoService;
		}

		switch (playMsg) {
		case PlayMsg.PLAY_MSG:
			// 用来执行mp3播放行为
			Log.d("listener", "playService-->");
			if (mp3info != null) {
				Log.d("listener", "Mp3playService--->" + mp3info.toString());
				// 判断歌是否有所改变
				play(mp3info);
			}
			break;
		case PlayMsg.PAUSE_MSG:
			// 用来暂停mp3
			Log.d("listener", "pauseService-->" + "mp3");

			pause();
			break;
		case PlayMsg.STOP_MSG:
			// 用来停止mp3的
			Log.d("listener", "stopService-->");
			stop();
			break;
		default:
			Log.d("mp3play", "error");
			break;

		}

		return super.onStartCommand(intent, flags, startId);
	}

	private void stop() {
		if (mediaMp3Play != null) {
			if (!isStop) {
				mediaMp3Play.stop();
				mediaMp3Play.release();

				isPlay = false;
				isStop = true;
			}
		}

	}

	private void pause() {
		// TODO Auto-generated method stub
		// 判断播放器是否能播放
		if (mediaMp3Play != null) {

			// 判断歌是否在播放
			if (!isStop) {
				if (!isPause) {
					// 暂停
					mediaMp3Play.pause();

					// 设置状态
					isPlay = true;
					isPause = true;
				} else {
					// 恢复播放
					mediaMp3Play.start();

					isPause = false;
					isPlay = true;
				}
			}
		}

	}

	private void play(Mp3Info mp3info) {

		if (mp3infoService == null) {
			mp3infoService = mp3info;
		}
		//判断路径是否相等,如果不相等,就重新创建一个MediaPlayer.
		if (!mp3infoService.getData().equals(mp3info.getData())) {
			mp3infoService = mp3info;
			stop();
			Log.d("listener", "path--->" + mp3info.getData());
			mediaMp3Play = new MediaPlayer();
			String path = mp3info.getData();
			try {
				mediaMp3Play.setDataSource(path);
				mediaMp3Play.prepare();
				mediaMp3Play.start();
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
			isPause = false;
			isStop = false;
		} else {
			if (!isPlay) {
				Log.d("listener", "path--->" + mp3info.getData());
				mediaMp3Play = new MediaPlayer();
				String path = mp3info.getData();
				try {
					mediaMp3Play.setDataSource(path);
					mediaMp3Play.prepare();
					mediaMp3Play.start();
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
				isPause = false;
				isStop = false;

			} else {
				// 暂停时恢复
				Log.d("listener", "playServiceResume-->");
				mediaMp3Play.start();
				isPlay = true;
				isPause = false;
			}
		}
	}

}
