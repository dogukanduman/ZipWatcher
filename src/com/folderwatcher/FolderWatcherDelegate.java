package com.folderwatcher;

public interface FolderWatcherDelegate {
	
	void created(String fileName);
	void deleted(String fileName);
	void modified(String fileName);
}
