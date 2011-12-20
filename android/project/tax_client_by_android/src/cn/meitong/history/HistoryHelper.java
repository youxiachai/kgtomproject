package cn.meitong.history;

import cn.meitong.values.TabValues;
import cn.meitong.values.DataBaseValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HistoryHelper extends SQLiteOpenHelper {
	
	
	
	
	
	public HistoryHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}
	
	
	public HistoryHelper(Context context){
		this(context,DataBaseValues.DATABASENAME,null,DataBaseValues.DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("history", "sql-->create"+DataBaseValues.CHECKTTABLE_CREATE);
		db.execSQL(DataBaseValues.CHECKTTABLE_CREATE);
		db.execSQL(DataBaseValues.DJTABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
