package kg.tom.events;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import  static kg.tom.events.Constants.*;

public class EventsData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "events.db";
	private static final int DATABASE_VERSION = 1;
	
	//创建一个数据库对象
	public EventsData(Context ctx){
		super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 
		String sql = "CREATE TABLE "+TABLE_NAME+"(" + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+TIME +" INTEGER,"+TITLE+" TEXT NOT NULL)";
		Log.d("data", sql);
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

}
