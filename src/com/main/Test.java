package com.main;

import com.zipwatcher.ZipWatcher;
import com.zipwatcher.ZipWatcherDelegate;

public class Test {
	public static void main(String[] args) {

		final String listenedFolderPath;

		if (args.length > 0) {
			listenedFolderPath = args[0];
		} else {
			listenedFolderPath = "/Users/ttdduman/downLoads";
		}

		System.out.println("Folder Path: " + listenedFolderPath);
		new ZipWatcher(listenedFolderPath, true, new ZipWatcherDelegate() {

			@Override
			public void unZipFile(String fileName) {
				System.out.println("UnZipped File:" + fileName);

			}
		});

	}

}
