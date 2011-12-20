package cn.meitong.history;

import java.sql.Date;

import cn.meitong.values.DataBaseValues;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HistoryUtils {
	
	//返回当前时间字符串
	public static String dateString(){
		Date sqlDate = new Date(System.currentTimeMillis());
		return sqlDate.toString();
	}
	
	private final Activity mContext;
	private final HistoryHelper historyHelper;
	private SQLiteDatabase sdb;
	
	public HistoryUtils(Activity mActivity){
		this.mContext = mActivity;
		historyHelper = new HistoryHelper(mActivity);
	}
	
	//插入记录
	public void insertResult(String tableName,String result,int type){
		//判断数据库是否存在该字段
		if(!isExist(tableName, result, type)){
			Log.d("history", "插入中");
			 sdb = historyHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();
			String date = dateString();
			cv.put(DataBaseValues.Column.DATE, date);
			cv.put(DataBaseValues.Column.RESULT, result);
			cv.put(DataBaseValues.Column.TYPE, type);
			sdb.insert(tableName, null, cv);
			sdb.close();
		}else{
			sdb.close();
			Log.d("history", "该字段已经存在");
		}
	}
	
	//查询记录
	public Cursor queryResult(String tableName){
		sdb = historyHelper.getReadableDatabase();
		String querySql = "select * from "+tableName;
		Cursor cursor = sdb.rawQuery(querySql, null);
		return cursor;
	}
	
	//判断是否存在
	public boolean isExist(String tableName,String result,int type){
		Log.d("history", "check isexists");
		Cursor cursor = queryResult(tableName);
		while(cursor.moveToNext()){
			String tempResult = cursor.getString(cursor.getColumnIndex(DataBaseValues.Column.RESULT));
			int tempType = cursor.getInt(cursor.getColumnIndex(DataBaseValues.Column.TYPE));
		
			if(tempResult.equals(result) && tempType == type){
				Log.d("history", "该字段已经存在");
				return true;
			}
		}
		return false;
	}
}
