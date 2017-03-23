# ZipWatcher
Handle downloaded or created Zip files in given path and unzip them. This code is a sample implementation of FolderWatcher 

Code Sample
```
new ZipWatcher(listenFolderPath, new ZipWatcherDelegate() {
			
			@Override
			public void unZipFile(String fileName) {
				System.out.println("UnZipped File:"+fileName);
				
			}
		});
