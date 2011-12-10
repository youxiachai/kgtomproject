package qing;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCardService {

    private String mypath = "";
    private String path_filename = "";

    public File createFile(String path, String filename) {
	this.mypath = getSDCard() + path;
	this.path_filename = this.mypath + filename;
	mkdir(mypath);
	File myfile = createNewFile(path_filename);

	return myfile;
    }

    public static String getSDCard() {
	String sdcard = Environment.getExternalStorageDirectory()
		.getAbsolutePath() + "/";
	return sdcard;
    }


    public File mkdir(String path) {
	File f = new File(path);
	if (!f.exists()) {
	    f.mkdirs();
	}
	return f;
    }

    public File createNewFile(String path_filename) {
	File f = new File(path_filename);
	if (!f.exists()) {
	    try {
		f.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return f;
    }
}