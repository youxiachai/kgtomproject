package cn.meitong.values;

public final class DataBaseValues {
	
	public static final String DATABASENAME = "history";
	public static final int DATABASE_VERSION = 1;
	public static final String CHECKTABLE = "checkhistory";
	public static final String QUERYTABLE = "queryhistory";
	public static final String DJTABLE = "zjdjhistory";
	
	public static final class Column {
		public static final String ID = "id";
		public static final String DATE = "date";
		public static final String RESULT = "result";
		public static final String TYPE = "type";
	}
	//SQL
	public static final String CHECKTTABLE_CREATE = "CREATE TABLE "+
	CHECKTABLE + " (id INTEGER primary key,date TEXT,result TEXT,type INTEGER)";
	
	public static final String DJTABLE_CREATE = "CREATE TABLE "+
			DJTABLE + " (id INTEGER primary key,date TEXT,result TEXT,type INTEGER)";
	
	
}
