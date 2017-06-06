package com.zipwatcher;

public interface ZipWatcherDelegate {
	
	public void unZippedFile(String fileName);
	public void unZipFinished(String outPutFolderName);
}
