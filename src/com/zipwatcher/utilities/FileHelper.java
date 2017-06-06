package com.zipwatcher.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {

	public static boolean isZipFile(String fileName) {

		String[] split = fileName.split("\\.");
		String ext = split[split.length - 1];

		if (!ext.equals("zip")) {
			return false;
		}

		return true;

	}

	public static void createDirectory(Path path) {

		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
	}

	public static void createDirectory(String path) {
		FileHelper.createDirectory(Paths.get(path));
	}

	public static String getFileName(String fileName) {

		String[] split = fileName.split("\\.");
		if (split.length > 1) {
			return split[0];
		}
		return fileName;
	}
}
