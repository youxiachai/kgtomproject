package cn.meitong.decoder;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.camera.CameraManager;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.history.HistoryHelper;
import cn.meitong.history.HistoryUtils;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.listener.ContentMananger;

import cn.meitong.values.ActionValues;
import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

public class CaptureActivity extends Activity implements SurfaceHolder.Callback {
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private TextView txtResult;
	private InactivityTimer inactivityTimer;

	private static final float BEEP_VOLUME = 0.10f;
	private static final String TAG = "CaptureActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.home);
		// 初始化 CameraManager
		CameraManager.init(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		txtResult = (TextView) findViewById(R.id.result);

		beepManger = new BeepManager(this);
		hasSurface = false;

		inactivityTimer = new InactivityTimer(this);
	}

	private BeepManager beepManger;

	@Override
	protected void onResume() {
		super.onResume();
		resetStatusView();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			// Activity 只是暂停了而已,没有停止,因此surface还是存在的
			// 因此,surfaceCreated() 不会启动,所以要再一次初始化
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = "GBK";

		// 这里应该有个设置Shareferences的地方

		// 成功声音
		//beepManger.updatePrefs();
		startTimer(true);
		inactivityTimer.onResume();
		
	}
	private Timer timer;
	
	private void startTimer(boolean setTimer){
		
		if(setTimer){
			timer = new Timer();
			
			if(to != null){
				to.cancel();
			}
			to =  new TimeOver(timeHandler, 60);
			to.mState = true;
			timer.schedule(to, 1000,1000);
		}else{
			Log.d("timer", "false!!!!!");
			to.mState = false;
		}
		
	}

	private Handler timeHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int ok = msg.what;
			Log.d("soap", "time over!!!!!!!");
			if(ok == 1){
				Log.d("soap", "ok");
				showDialog(SCAN_TIMEOVER);
			}
		};
	};
	TimeOver to;
	/**
	 * 用于初始化状态
	 */
	private void resetStatusView() {
		viewfinderView.setVisibility(View.VISIBLE);

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		inactivityTimer.onPause();
		startTimer(false);
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
			if (handler == null) {
				handler = new CaptureActivityHandler(this, decodeFormats,
						characterSet);
			}
		} catch (IOException ioe) {
			Log.w(TAG, ioe);
			// displayFrameworkBugMessageAndExit();

		} catch (RuntimeException e) {
			Log.w(TAG, "相机出现错误", e);

		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d("capture", "gaile!!!!!!");
		CameraManager.get().startPreview();

		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	public String result;

	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		//绘制返回的截图
		// viewfinderView.drawResultBitmap(barcode);

		// 用于处理以后接口的
		// ResultHandle handle = new ResultHandle();

		if (barcode == null) {
			Log.d("capture", "错误!!!");
		} else {
			Log.d("capture", obj.getText());
			//播放声音
			//beepManger.playBeepSoundAndVibrate();
			result = obj.getText();
			startTimer(false);
			Log.d("soap", result);
			//保存到历史记录
			new HistoryUtils(this).insertResult(DataBaseValues.CHECKTABLE, result, ResultValues.QueryType.qRcode);
			//转送返回的
			Intent intent = new ContentMananger(CaptureActivity.this)
			.setIntent(MainTabActivity.class, ResultValues.QueryKey.QRCODE, result, ActionValues.SCAN_SUCCESS);
			//intent.setClass(packageContext, cls);
			startActivity(intent);
			//-----
//			Intent intent = new Intent().setClass(this, SoapTest.class);
//			intent.putExtra(ResultValues.QueryKey.QRCODE, result);
//			startActivity(intent);
			
		}

	}
	
	private ResultHandle resultHandle;
	public AlertDialog alertDialogBuilder(String title) {


		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(title)
				.setCancelable(false)
				.setPositiveButton(R.string.dialog_back,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent().setClass(
										CaptureActivity.this,
										MainTabActivity.class).setAction(ActionValues.SCAN_BACK);
					
								//----------------进行实时查询
								startActivity(intent);
						
								//-------------
								
							}
						})
	
				.setNegativeButton(R.string.dialog_cancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
								Log.d("mainTab","resume----->");
								onPause();
								onResume();
							}
						});
		return builder.create();
	}

	AlertDialog resultDialog;
	private final static int SCAN_DIALOG = 1;
	private final static int SCAN_TIMEOVER = 2;
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case SCAN_DIALOG:
			resultDialog = alertDialogBuilder("返回到主界面");
			break;
		case SCAN_TIMEOVER:
			resultDialog =  alertDialogBuilder("扫描超时");
			break;
		default:
			break;
		}

		return resultDialog;
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {

		MenuInflater backflater = getMenuInflater();
		backflater.inflate(R.menu.capture_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 返回
		switch (item.getItemId()) {
		case R.id.capture_back:
			Intent intent = new Intent().setClass(this, MainTabActivity.class);
			intent.setAction(ActionValues.SCAN_BACK);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
           showDialog(SCAN_DIALOG);
            return false;
        }
        return false;
    }
}