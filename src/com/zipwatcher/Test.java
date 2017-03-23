package com.zipwatcher;

import com.folderwatcher.FolderWatcher;
import com.folderwatcher.FolderWatcherDelegate;

public class Test
{
	
	public static void main(String[] args) {
	   
		 String listenedFolderPath;
		 listenedFolderPath="/Users/ttdduman/downLoads";
		 
		if (args.length > 0) {
			listenedFolderPath = args[0];
		}
		System.out.println("Folder Path: " + listenedFolderPath);
		new ZipWatcher(listenedFolderPath, new ZipWatcherDelegate() {
			
			@Override
			public void unZipFile(String fileName) {
				System.out.println("UnZipped File:"+fileName);
				
			}
		});
		
		
		
	}	

}
