package kg.tom.events;

import static android.provider.BaseColumns._ID;
import static kg.tom.events.Constants.CONTENT_URI;
import static kg.tom.events.Constants.TIME;
import static kg.tom.events.Constants.TITLE;
import android.app.ListActivity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

public class Events extends ListActivity {
	private EventsData events;
	private Button delete;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //events = new EventsData(this);
       
        	addEvent("hello22222, tom");
        	Cursor cursor = getEvnets();
        	showEvents(cursor);
        	delete = (Button) findViewById(R.id.delete);
        	delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					deleteEvent();
				}

			
			});
        
        
    }
	private void deleteEvent() {
//		ContentValues values = new ContentValues();
//		values.put(_ID, "1");
		Uri uri = ContentUris.withAppendedId(CONTENT_URI, 1);
		
		getContentResolver().delete(uri, null, null);
		
	}
    //定义查询字段
    private static int[] TO = {R.id.rowid,R.id.time,R.id.title,};
    
    //查询一个结果
	private void showEvents(Cursor cursor) {
		//创建一个大字符串
//		StringBuilder builder = new StringBuilder("Saved events:\n");
//		while (cursor.moveToNext()){
//			long id = cursor.getLong(0);
//			long time = cursor.getLong(1);
//			String title = cursor.getString(2);
//			builder.append(id).append(": ");
//			builder.append(time).append(": ");
//			builder.append(title).append("\n");	
//		}
//		//在屏幕显示
//		TextView text = (TextView) findViewById(R.id.text);
//		text.setText(builder);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item,cursor, FROM, TO);
		setListAdapter(adapter);
		
		
	}
	private static String[] FROM ={_ID, TIME, TITLE, };
	private static String ORDER_BY = TIME +" DESC";
	
	//运行一个查询
	private Cursor getEvnets() {
//		SQLiteDatabase db = events.getReadableDatabase();
//		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
//		startManagingCurson(cursor);
//		
//		return cursor;
		//使用contentprovider
		return managedQuery(CONTENT_URI, FROM, null, null, ORDER_BY);
	}
	private void startManagingCurson(Cursor cursor) {
		
	}
	private void addEvent(String string) {
		//添加新的一条记录
//		SQLiteDatabase db = events.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(TIME, System.currentTimeMillis());
//		values.put(TITLE, string);
//		db.insertOrThrow(TABLE_NAME, null, values);
		//使用contentProider
		ContentValues values= new ContentValues();
		values.put(TIME, System.currentTimeMillis());
		values.put(TITLE, string);
		getContentResolver().insert(CONTENT_URI, values);
	}
}