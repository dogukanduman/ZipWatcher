package com.zipwatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.folderwatcher.FolderWatcher;
import com.folderwatcher.FolderWatcherDelegate;

public class ZipWatcher implements FolderWatcherDelegate {

	private ZipWatcherDelegate delegate;
	private String listenPath;

	public ZipWatcher(String listenPath) {

		this.listenPath = listenPath;

		new FolderWatcher(this, listenPath);
	}

	public ZipWatcher(String listenPath,ZipWatcherDelegate delegate) {
		this.listenPath = listenPath;
        this.delegate=delegate;
		new FolderWatcher(this, listenPath);
	}
	
	
	
	@Override
	public void created(String fileName) {
		if (isZipFile(fileName)) {
			System.out.println("Zip File: " + fileName);
			unZip(fileName);
		}
	}

	@Override
	public void deleted(String fileName) {


	}

	@Override
	public void modified(String fileName) {
		

	}

	public void unZip(String inPutFileName) {

		try {
			byte[] buffer = new byte[1024];
			ZipInputStream zis = new ZipInputStream(new FileInputStream(this.listenPath + "/" + inPutFileName));
			ZipEntry ze = zis.getNextEntry();
			String fileName = null;
			while (ze != null) {

				fileName = ze.getName();
				File newFile = new File(fileName);

				FileOutputStream fos = new FileOutputStream(this.listenPath + "/" + newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				delegate.unZipFile(fileName);
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isZipFile(String logFileName) {

		String[] split = logFileName.split("\\.");
		String ext = split[split.length - 1];

		if (!ext.equals("zip")) {
			return false;
		}
		
		return true;

	}

	public void setDelegate(ZipWatcherDelegate delegate) {
		this.delegate = delegate;
	}
}
