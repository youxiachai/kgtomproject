package kg.tom.events;

import android.net.Uri;
import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	public static final String TABLE_NAME = "events";
	
	//栏位名字
	public static final String TIME = "time";
	public static final String TITLE = "title";
	public static final String AUTHORITY = "kg.tom.events";
	public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TABLE_NAME);
}
