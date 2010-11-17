package kg.tom.events;

import static kg.tom.events.Constants.*;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class EventsProvider extends ContentProvider {
	private static final int EVENTS = 1;
	private static final int EVENTS_ID = 2;
	
	//MIME事件目录
	private static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.tom.event";
	//MIME单独事件
	private static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.tom.event";
	
	private EventsData events;
	private UriMatcher uriMatcher;
	

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = events.getWritableDatabase();
		int count;
		switch (uriMatcher.match(uri)){
		case EVENTS:
			Log.d("uri", "events");
			count = db.delete(TABLE_NAME, selection, selectionArgs);
			break;
		case EVENTS_ID:
			
			long id = Long.parseLong(uri.getPathSegments().get(1));
			Log.d("uri", String.valueOf(id));
			count = db.delete(TABLE_NAME, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URi " + uri);
		}
		//通知改变
		getContext().getContentResolver().notifyChange(uri, null);	
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)){
		case EVENTS:
			return CONTENT_TYPE;
		case EVENTS_ID:
			return CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = events.getWritableDatabase();
		if (uriMatcher.match(uri) != EVENTS){
			throw new IllegalArgumentException("Unknown URI"+uri);
		}
		
		//插入数据到数据库
		long id = db.insertOrThrow(TABLE_NAME, null, values);
		//通知改变
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, id);
		getContext().getContentResolver().notifyChange(newUri, null);
		
		return null;
	}

	@Override
	public boolean onCreate() {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "events", EVENTS);
		uriMatcher.addURI(AUTHORITY, "events/#", EVENTS_ID);
		String urixx= uriMatcher.toString();
		Log.d("uri", urixx);
		events = new EventsData(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if (uriMatcher.match(uri) == EVENTS_ID){
			long id = Long.parseLong(uri.getPathSegments().get(1));
			selection = appendRowId(selection,id);
		}
		
		//获得数据库并运行查询
		SQLiteDatabase db = events.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}
	//添加测试用SQL
	private String appendRowId(String selection, long id) {
		
		return _ID + "=" + id + (!TextUtils.isEmpty(selection)?" AND ("+selection+')': "");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = events.getWritableDatabase();
		int count;
		switch (uriMatcher.match(uri)){
		case EVENTS:
			count = db.update(TABLE_NAME, values, selection, selectionArgs);
			break;
		case EVENTS_ID:
			long id = Long.parseLong(uri.getPathSegments().get(1));
			count = db.update(TABLE_NAME, values, selection, selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI" + uri);
		}
		//通知改变
		getContext().getContentResolver().notifyChange(uri, null);
		
		return count;
	}

}
