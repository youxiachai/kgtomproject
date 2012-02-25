package com.achai.shop.backup;

public interface CompletionListener {
	void onBackupComplete();
	void onRestoreComplete();
	void onError(int errorCode);
}
