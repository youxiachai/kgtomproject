package kg.tom.view;




import kg.tom.R;
import kg.tom.utils.ConstantsVar;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AlbumArtistSwitcherView extends View {
	
	private final String TAG = "AlbumArtristSwitcherView";
	
	
	
	//全局状态
	boolean	mTouching = false;
	//作者 = 0, 专辑 = 1
	float	mTargetPosition = 1;
	//作者 = 0, 专辑 = 1
	float	mPosition = 1; 
	float	mTargetPresence = 0;
	float	mPresence = 0;
	float	mCurrentLockPosition;
	float	mElementDensityPixels;
	
	
	//全局变量
	Handler	mStateChangeHandler;
	int		mWidth;
	int		mHeight;
	Paint	mBgPaint;
	int[]	mBgGradientColors = 
	{
			Color.argb(255, 240, 240, 240),
			Color.argb(255, 172, 172, 172)
	};
	
	float[]	mBgGradientColorPositions =
		{
				0.f,
				1f
		};
		Paint	mLightSeparatorPaint;
		int		mLightSeparatorColor = Color.argb(255, 240, 240, 240);
		Paint	mDarkSeparatorPaint;
		int		mDarktSeparatorColor = Color.argb(255, 80, 80, 80);
		
		Paint	mSlidingTextPaint;
		int[]	mSlidingTextGradientColors = 
		{
				Color.argb(255, 16, 16, 16),
				Color.argb(255, 56, 56, 56)
		};
		float[]	mSlidingTextGradientColorPositions =
		{
				0.f,
				1f
		};
		
		Paint	mSlidingTextLightHintingPaint;
		int		mSlidingTextLightHintingColor = Color.argb(255, 172, 172, 172);
		Paint	mSlidingTextDarkHintingPaint;
		int		mSlidingTextDarkHintingColor = Color.argb(255, 0, 0, 0);
		
		float	mSlidingTextSize;
		
		Paint	mCategoryIndicatorPaint;
		int[]	mCategoryIndicatorGradientColors = 
		{
				Color.argb(255, 224, 224, 224),
				Color.argb(255, 32, 32, 32)
		};
		float[]	mCategoryIndicatorGradientColorPositions =
		{
				0.f,
				1f
		};
		
		Paint	mCategoryHighlightIndicatorPaint;
		int[]	mCategoryHighlightIndicatorGradientColors = 
		{
				Color.argb(255, 240, 240, 240),
				Color.argb(255, 96, 96, 96)
		};
		float[]	mCategoryHighlightIndicatorGradientColorPositions =
		{
				0.f,
				1f
		};
		
		Paint	mCategoryIndicatorLightHintingPaint;
		int		mCategoryIndicatorLightHintingColor = Color.argb(255, 172, 172, 172);
		Paint	mCategoryIndicatorDarkHintingPaint;
		int		mCategoryIndicatorDarkHintingColor = Color.argb(255, 0, 0, 0);
		
		String	mAlbumsString;
		String	mArtistsString;
		String	mSongsString;
		
		//优化参数
		int 	oAlpha = 0;
		String	oSlidingText;
		float	oSlidingTextPosition;
		float	oCategoryIndicationPosition;
		
		
		
		/**
		 * 
		 * @param touching
		 * @param x
		 * @param y
		 */
		public void setTouching(boolean touching, float x, float y)
		{
			Log.i(TAG, "SetTouching: "+touching);
			
			mTouching = touching;
			if(!mTouching)
			{
				snapPosition();
				if(!positionNeedsUpdate())
					mTargetPresence = 0.f; 
			}
			else
			{
				mCurrentLockPosition = Math.round(mPosition);
				mFadeOutHandler.removeCallbacksAndMessages(null);
				mTargetPresence = 1.f;
			}
			
			invalidate();
		}
		
		/**
		 * 
		 * @param incrementInPx
		 */
		public void changePosition(float incrementInPx, float x, float y)
		{
//			Log.i(TAG, "changePosition: "+incrementInPx);
			
			mPosition -= incrementInPx/mWidth;
			if(mPosition < 0)
				mPosition += ConstantsVar.SWITCHER_CATEGORY_COUNT;
			mTargetPosition = mPosition;
			invalidate();
		}
		
		private void snapPosition()
		{
			if(Math.abs(mPosition-mCurrentLockPosition) > ConstantsVar.SWITCHER_MOVEMENT_REQUIRED_TO_SWITCH)
			{
				if(Math.abs(mPosition - mCurrentLockPosition) < 1)
					mTargetPosition = mCurrentLockPosition + Math.signum(mPosition-mCurrentLockPosition);
				else
					mTargetPosition = mCurrentLockPosition + 
						Math.signum(mPosition-mCurrentLockPosition) * (float)Math.floor(Math.abs(mPosition-mCurrentLockPosition));
				
			}
			// Let's just make sure that this is a round number
			mTargetPosition = Math.round(mTargetPosition);
			
			Log.i(TAG, "mPosition: "+mPosition+" mTargetPosition: "+mTargetPosition+" mCurrentLock: "+mCurrentLockPosition);
			
//			if(mTargetPosition < 0)
//				mTargetPosition = 0;
//			else if(mTargetPosition > Constants.SWITCHER_CATEGORY_COUNT - 1)
//				mTargetPosition = Constants.SWITCHER_CATEGORY_COUNT - 1;
		}
		
		
	
	
	//构造函数
	public AlbumArtistSwitcherView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.i(TAG, "OK!!!!!!: ");
		mElementDensityPixels = context.getResources().getDimension(R.dimen.button_default_width);
		initNonGraphicVars(context);
	}
	
	private void initNonGraphicVars(Context context)
	{
		/** 
		 * Some strings defined in the resources 
		 */
		Log.i(TAG, "OK-->init ");
		mAlbumsString = 
			context.getResources().
				getString(ConstantsVar.SWITCHER_CAT_ALBUM_STRING_RES);
		mArtistsString = 
			context.getResources().
				getString(ConstantsVar.SWITCHER_CAT_ARTIST_STRING_RES);
		mSongsString = 
			context.getResources().
				getString(ConstantsVar.SWITCHER_CAT_SONG_STRING_RES);
	
		
		/** 
		 * Initial Position 
		 * 初始化和改变小圆点的设置
		 */
		switch(PreferenceManager.getDefaultSharedPreferences(context).
					getInt(ConstantsVar.prefkey_mBrowseCatMode, ConstantsVar.BROWSECAT_ALBUM))
		{
	
		case ConstantsVar.BROWSECAT_ALBUM:
			mPosition = ConstantsVar.SWITCHER_CAT_ALBUM;
			mTargetPosition = ConstantsVar.SWITCHER_CAT_ALBUM;
			break;
		case ConstantsVar.BROWSECAT_ARTIST:
			mPosition = ConstantsVar.SWITCHER_CAT_ARTIST;
			mTargetPosition = ConstantsVar.SWITCHER_CAT_ARTIST;
			break;
		case ConstantsVar.BROWSECAT_SONG:
			mPosition = ConstantsVar.SWITCHER_CAT_SONG;
			mTargetPosition = ConstantsVar.SWITCHER_CAT_SONG;
			break;
		}
	}
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = w;
		mHeight = h;

		initDrawingVars();

	}
	
	/**
	 * initDrawingVars
	 */
	private void initDrawingVars()
	{
		/** Background */
		mBgPaint = new Paint();
		mBgPaint.setAntiAlias(true);
		mBgPaint.setDither(true);
		mBgPaint.setShader(
				new LinearGradient(
						0, 0, 
						0, mHeight*.5f, 
						mBgGradientColors,
						mBgGradientColorPositions,
						TileMode.CLAMP));
		
		/** Contrast Separators */
		mLightSeparatorPaint = new Paint();
		mLightSeparatorPaint.setColor(mLightSeparatorColor);
//		mLightSeparatorPaint.setAntiAlias(true);
//		mLightSeparatorPaint.setDither(true);
		
		mDarkSeparatorPaint = new Paint();
		mDarkSeparatorPaint.setColor(mDarktSeparatorColor);
//		mDarkSeparatorPaint.setAntiAlias(true);
//		mDarkSeparatorPaint.setDither(true);
		
		/** Sliding Text */
		mSlidingTextSize = mHeight/2 * ConstantsVar.SWITCHER_TEXT_RATIO;

		mSlidingTextPaint = new Paint();
		mSlidingTextPaint.setAntiAlias(true);
		mSlidingTextPaint.setDither(true);
		mSlidingTextPaint.setTextAlign(Align.CENTER);
		mSlidingTextPaint.setTextSize(mSlidingTextSize);
		mSlidingTextPaint.setShader(
				new LinearGradient(
						0, 0, 
						0, mHeight*.5f, 
						mSlidingTextGradientColors,
						mSlidingTextGradientColorPositions,
						TileMode.CLAMP));
		
		mSlidingTextDarkHintingPaint = new Paint();
		mSlidingTextDarkHintingPaint.setAntiAlias(true);
		mSlidingTextDarkHintingPaint.setDither(true);
		mSlidingTextDarkHintingPaint.setTextAlign(Align.CENTER);
		mSlidingTextDarkHintingPaint.setTextSize(mSlidingTextSize);
		mSlidingTextDarkHintingPaint.setColor(mSlidingTextDarkHintingColor);
		
		mSlidingTextLightHintingPaint = new Paint();
		mSlidingTextLightHintingPaint.setAntiAlias(true);
		mSlidingTextLightHintingPaint.setDither(true);
		mSlidingTextLightHintingPaint.setTextAlign(Align.CENTER);
		mSlidingTextLightHintingPaint.setTextSize(mSlidingTextSize);
		mSlidingTextLightHintingPaint.setColor(mSlidingTextLightHintingColor);
		
		/** Category Indicator */
		mCategoryIndicatorPaint = new Paint();
		mCategoryIndicatorPaint.setAntiAlias(true);
		mCategoryIndicatorPaint.setDither(true);
		mCategoryIndicatorPaint.setShader(
				new LinearGradient(
						0, mHeight * .85f - mHeight*.5f*ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO, 
						0, mHeight * .85f + mHeight*.5f*ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO, 
						mCategoryIndicatorGradientColors,
						mCategoryIndicatorGradientColorPositions,
						TileMode.CLAMP));
		/** Category Highlight Indicator */
		mCategoryHighlightIndicatorPaint = new Paint();
		mCategoryHighlightIndicatorPaint.setAntiAlias(true);
		mCategoryHighlightIndicatorPaint.setDither(true);
		mCategoryHighlightIndicatorPaint.setShader(
				new LinearGradient(
						0, mHeight * .85f - mHeight*.5f*ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO, 
						0, mHeight * .85f + mHeight*.5f*ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO, 
						mCategoryHighlightIndicatorGradientColors,
						mCategoryHighlightIndicatorGradientColorPositions,
						TileMode.CLAMP));
		
		this.invalidate();
	}
	
	/**
	 * 
	 */
	private void updatePresence()
	{
		if(mTargetPresence != mPresence)
		{
			if(Math.abs(mTargetPresence - mPresence) > ConstantsVar.SWITCHER_PRESENCE_UPDATE_STEP)
				mPresence +=
					Math.signum(mTargetPresence - mPresence)
					*
					ConstantsVar.SWITCHER_PRESENCE_UPDATE_STEP;
			else
				mPresence = mTargetPresence;
		}
	}
	
	/**
	 * 
	 */
	private void updatePosition()
	{
		if(mTargetPosition != mPosition)
		{
			if(Math.abs(mTargetPosition - mPosition) > ConstantsVar.SWITCHER_PRESENCE_UPDATE_STEP)
				mPosition +=
					Math.signum(mTargetPosition - mPosition)
					*
					ConstantsVar.SWITCHER_PRESENCE_UPDATE_STEP;
			else
				mPosition = mTargetPosition;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean positionNeedsUpdate()
	{
		if(mTargetPosition == mPosition && mPosition % 1.f == 0.f)
			return false;
		else
			return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean presenceNeedsUpdate()
	{
		if(mTargetPresence == mPresence && (mPresence == 0.f || mPresence == 1.f))
			return false;
		else
			return true;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	private boolean needsUpdate()
//	{
//		if(mTargetPosition == mPosition && mPosition / 1.f == 0)
//			if(mTargetPresence == mPresence && (mPresence == 0.f || mPresence == 1.f))
//			{
//				Log.i(TAG, 
//						"mPresence: "+mPresence+
//						"mTargetPresence: "+mTargetPresence+
//						"mPosition: "+mPosition+
//						"mTargetPosition: "+mTargetPosition);
//				return false;
//			}
//
//		Log.i(TAG, 
//				"mPresence: "+mPresence+
//				"mTargetPresence: "+mTargetPresence+
//				"mPosition: "+mPosition+
//				"mTargetPosition: "+mTargetPosition);
//		return true;
//	}
	
	/**
	 * State Change Handler
	 */
	public void setStateChangeHandler(Handler stateChangeHandler)
	{
		this.mStateChangeHandler = stateChangeHandler;
	}
	
	/**
	 * OnDraw
	 */
	@Override
	public void onDraw(Canvas canvas)
	{
		Log.i(TAG, "OK-->init onDraw ");
		
		/**
		 * Set 'presence'
		 */
		updatePresence();
		oAlpha = (int) 
			(ConstantsVar.SWITCHER_LOW_PRESENCE_ALPHA + 
			mPresence * (ConstantsVar.SWITCHER_HIGH_PRESENCE_ALPHA - ConstantsVar.SWITCHER_LOW_PRESENCE_ALPHA));
		
		/**
		 * Set current Position
		 */
		updatePosition();
		
		/**
		 * Draw background
		 */
		drawOverlayBg(canvas, oAlpha);
		
		/**
		 * Draw separators
		 */
		drawOverlaySeparators(canvas, oAlpha);
		
		/**
		 * Draw text
		 */
		drawSlidingText(canvas, oAlpha, mPresence);
		
		/**
		 * Draw state indicator
		 */
		drawStateIndicator(canvas, oAlpha, mPresence); // two ways of drawing depending on whether is odd or even number of categories
		
		/**
		 * Keep drawing?
		 */
		if(!mTouching)
		{
			if(!positionNeedsUpdate())
			{
				if(!presenceNeedsUpdate() && mTargetPresence == 1.f)
				{
					// schedule fade out
					mFadeOutHandler.removeCallbacksAndMessages(null);
					mFadeOutHandler.sendEmptyMessageDelayed(
							0, 
							ConstantsVar.SWITCHER_PERSIST_SWITCH_PERIOD);
					return;
				}
				else
				{
					mTargetPresence = 0.f; // fade out
					if(!presenceNeedsUpdate())
					{
						triggerUpdate();
						return; // animations are over
					}
				}
			}
		}
			
		invalidate();
//		Log.i(TAG, 
//			" mPresence: "+mPresence+
//			" mTargetPresence: "+mTargetPresence+
//			" mPosition: "+mPosition+
//			" mTargetPosition: "+mTargetPosition);
	}
	
	private void triggerUpdate()
	{
		if(mStateChangeHandler != null)
		{
			switch(((int)mPosition)%ConstantsVar.SWITCHER_CATEGORY_COUNT)
			{
			case ConstantsVar.SWITCHER_CAT_ALBUM:
				mStateChangeHandler.sendEmptyMessage(ConstantsVar.BROWSECAT_ALBUM);
				break;
			case ConstantsVar.SWITCHER_CAT_ARTIST:
				mStateChangeHandler.sendEmptyMessage(ConstantsVar.BROWSECAT_ARTIST);
				break;
			case ConstantsVar.SWITCHER_CAT_SONG:
				mStateChangeHandler.sendEmptyMessage(ConstantsVar.BROWSECAT_SONG);
				break;
			default:
				Log.i(TAG, ""+(int)mPosition);
				break;
			}
		}
	}
	
	Handler mFadeOutHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			mTargetPresence = 0.f;
			invalidate();
		}
	};
	
	/**
	 * 
	 * @param canvas
	 * @param alpha
	 */
	private void drawOverlayBg(Canvas canvas, int alpha)
	{
		mBgPaint.setAlpha(alpha);
		canvas.drawRect(0, 0, mWidth, mHeight/2, mBgPaint);
	}
	
	/**
	 * 
	 * @param canvas
	 * @param alpha
	 */
	private void drawOverlaySeparators(Canvas canvas, int alpha)
	{
		mDarkSeparatorPaint.setAlpha(alpha);
		mLightSeparatorPaint.setAlpha(alpha);
		canvas.drawLine(0, 0, mWidth, 0, mDarkSeparatorPaint);
		canvas.drawLine(0, 1, mWidth, 1, mLightSeparatorPaint);
		canvas.drawLine(0, mHeight/2-1, mWidth, mHeight/2-1, mDarkSeparatorPaint);
		canvas.drawLine(0, mHeight/2-0, mWidth, mHeight/2-0, mLightSeparatorPaint);
	}
	
	/**
	 * 
	 * @param canvas
	 * @param alpha
	 */
	private void drawSlidingText(Canvas canvas, int alpha, float presence)
	{
		/** set paint alpha */
		mSlidingTextPaint.setAlpha((int) (presence * 255));
		mSlidingTextLightHintingPaint.setAlpha((int) (presence * 255));
		mSlidingTextDarkHintingPaint.setAlpha((int) (presence * 255));
		
		/** cycle all categories */
		for(int i = 0; i<ConstantsVar.SWITCHER_CATEGORY_COUNT; i++)
		{
			switch(i)
			{
			case ConstantsVar.SWITCHER_CAT_ARTIST:
				oSlidingText = mArtistsString;
				oSlidingTextPosition = ConstantsVar.SWITCHER_CAT_ARTIST - mPosition % ConstantsVar.SWITCHER_CATEGORY_COUNT;
				// rounding effects
				if(ConstantsVar.SWITCHER_CATEGORY_COUNT - mPosition%ConstantsVar.SWITCHER_CATEGORY_COUNT < 1)
					oSlidingTextPosition += ConstantsVar.SWITCHER_CATEGORY_COUNT;
//				if(Math.ceil(mPosition) >= Constants.SWITCHER_CATEGORY_COUNT)
//					oSlidingTextPosition += (int) mPosition / Constants.SWITCHER_CATEGORY_COUNT * Constants.SWITCHER_CATEGORY_COUNT;
				break;
			case ConstantsVar.SWITCHER_CAT_ALBUM:
				oSlidingText = mAlbumsString;
				oSlidingTextPosition = ConstantsVar.SWITCHER_CAT_ALBUM - mPosition % ConstantsVar.SWITCHER_CATEGORY_COUNT;
				break;
			case ConstantsVar.SWITCHER_CAT_SONG:
				oSlidingText = mSongsString;		
				oSlidingTextPosition = ConstantsVar.SWITCHER_CAT_SONG - mPosition % ConstantsVar.SWITCHER_CATEGORY_COUNT;
				break;
			}
			/** text shadow */
			/** 'light' hinting */
			canvas.drawText(
					oSlidingText, 
					(.5f + oSlidingTextPosition)*mWidth, 
					mHeight/2/2 + mSlidingTextPaint.getTextSize()/3 + 1, 
					mSlidingTextLightHintingPaint);
			/** 'dark' hinting */
//			canvas.drawText(
//					oSlidingText, 
//					(.5f + oSlidingTextPosition)*mWidth, 
//					mHeight/2/2 + mSlidingTextPaint.getTextSize()/3 - 1, 
//					mSlidingTextDarkHintingPaint);
			/** 'main' text' */
			canvas.drawText(
					oSlidingText, 
					(.5f + oSlidingTextPosition)*mWidth, 
					mHeight/2/2 + mSlidingTextPaint.getTextSize()/3, 
					mSlidingTextPaint);
		}
	}
	
	/**
	 * 
	 * @param canvas
	 * @param alpha
	 * @param presence
	 */
	private void drawStateIndicator(Canvas canvas, int alpha, float presence)
	{
		for(int k=0; k<2; k++)
		{
			
			/** get initial state position */
			if(ConstantsVar.SWITCHER_CATEGORY_COUNT  % 2 == 0)
			{
				if(k == 0)
				{
					oCategoryIndicationPosition = mElementDensityPixels * .5f;
				}
				else
				{
					oCategoryIndicationPosition = mWidth - mElementDensityPixels * .5f;
				}
//				oCategoryIndicationPosition = mWidth * .5f;
				oCategoryIndicationPosition -= 
					// each circle
					ConstantsVar.SWITCHER_CATEGORY_COUNT/2 * (mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO) * 2.f
					+
					// space between circles
					(ConstantsVar.SWITCHER_CATEGORY_COUNT/2 - 1) *  (mHeight*.5f) * 
						(2.f * ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO * ConstantsVar.SWITCHER_CAT_CIRCLE_SPACING)
					+
					// half spacing
					(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO) * 2.f * 
						ConstantsVar.SWITCHER_CAT_CIRCLE_SPACING * .5f;
			}
			else
			{
				if(k == 0)
				{
//					oCategoryIndicationPosition = mElementDensityPixels * .5f;
					oCategoryIndicationPosition = mElementDensityPixels * .25f;
				}
				else
				{
//					oCategoryIndicationPosition = mWidth - mElementDensityPixels * .5f;
					oCategoryIndicationPosition = mWidth - mElementDensityPixels * .75f;
				}
////				oCategoryIndicationPosition = mWidth * .5f;
//				oCategoryIndicationPosition -= 
//					// each circle
//					Constants.SWITCHER_CATEGORY_COUNT/2 * (mHeight*.5f) * (Constants.SWITCHER_CAT_CIRCLE_RATIO) * 2.f
//					+
//					// space between circles
//					(Constants.SWITCHER_CATEGORY_COUNT/2 - 1) *  (mHeight*.5f) * 
//						(2.f * Constants.SWITCHER_CAT_CIRCLE_RATIO * Constants.SWITCHER_CAT_CIRCLE_SPACING)
//					+
//					// half spacing
//					(mHeight*.5f) * (Constants.SWITCHER_CAT_CIRCLE_RATIO) * 2.f * 
//						Constants.SWITCHER_CAT_CIRCLE_SPACING * .5f;			
			}
			
			/** Highlight of current state */
			float idx;
			if(mPosition%ConstantsVar.SWITCHER_CATEGORY_COUNT > ConstantsVar.SWITCHER_CATEGORY_COUNT - 1)
				idx = (float) (1.f + (Math.floor(mPosition)-mPosition));
			else
				idx = (float) (Math.floor(mPosition)%ConstantsVar.SWITCHER_CATEGORY_COUNT + (mPosition - Math.floor(mPosition)));
			canvas.drawCircle(
					oCategoryIndicationPosition
					+
					// each circle
					(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO) * 2.f * idx
					+
					// each space between circle
					(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO * 2.f) * 
						ConstantsVar.SWITCHER_CAT_CIRCLE_SPACING * idx
					+
					// center offset
					(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO), 
					
					mHeight*.85f, // cy 
					(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO) * 1.28f, 
					mCategoryHighlightIndicatorPaint);
			
			
			/** draw all states */
			for(int i=0; i<ConstantsVar.SWITCHER_CATEGORY_COUNT; i++)
			{	
				/** All states */
				canvas.drawCircle(
						oCategoryIndicationPosition
						+
						// each circle
						(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO) * 2.f * i
						+
						// each space between circle
						(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO * 2.f) * 
							ConstantsVar.SWITCHER_CAT_CIRCLE_SPACING * i
						+
						// center offset
						(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO), 
						
						mHeight*.85f, // cy 
						(mHeight*.5f) * (ConstantsVar.SWITCHER_CAT_CIRCLE_RATIO), 
						mCategoryIndicatorPaint);
			}
	
			
		}
	}
	
	
	

}
