
package kg.tom;

import java.io.File;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * @author Tom_achai
 * @date 2011-11-10
 * 
 */
public class VideoPlayerActivity extends Activity {
	
	VideoView mVideoView;
	MediaController mMediaController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.videoplayer);
		mVideoView = (VideoView) findViewById(R.id.video);
		mMediaController = new MediaController(this);
		
		File video = new File("/mnt/sdcard/test.3gp");
		if(video.exists()){
			//获取视频的路径
			mVideoView.setVideoPath(video.getAbsolutePath());
			//设置mMediaController与VideoView建立关联
			mVideoView.setMediaController(mMediaController);
			//设置mController 与 videoView建立关联
			mMediaController.setMediaPlayer(mVideoView);
		//	mVideoView.requestFocus();
		}
	}
}
