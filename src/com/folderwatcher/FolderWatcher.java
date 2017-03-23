package com.folderwatcher;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;


public class FolderWatcher {

	private FolderWatcherDelegate delegate;
	private String listenPath;
	
	public FolderWatcher(FolderWatcherDelegate delegate, String listenPath) {
		super();
		this.delegate = delegate;
		this.listenPath = listenPath;
		start();
	}
	public void start()
	{
		System.out.println(this.listenPath+" is watching...");
		WatchService watcher;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			Path dir = Paths.get(this.listenPath);
			dir.register(watcher, ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);
			
			while (true) {
				WatchKey key;
				try {
					key = watcher.take();
				} catch (InterruptedException ex) {
					return;
				}
				for (WatchEvent<?> event : key.pollEvents()) {
					eventHandler(event);
				}
				boolean valid = key.reset();
				if (!valid) {
					break;
				}	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void eventHandler(WatchEvent<?> event)
	{
		
		WatchEvent.Kind<?> kind = event.kind();
		@SuppressWarnings("unchecked")
		WatchEvent<Path> ev = (WatchEvent<Path>) event;
		Path fileName = ev.context();
		switch (kind.name()) {
        case "ENTRY_MODIFY":
        	delegate.modified(fileName.getFileName().toString());
            break;
        case "ENTRY_DELETE":
        	delegate.deleted(fileName.getFileName().toString());
            break;
        case "ENTRY_CREATE":
        	delegate.created(fileName.getFileName().toString());
            break;
        default:
            System.out.println("Event not expected " + event.kind().name());
          }
		
	}

	public FolderWatcherDelegate getDelegate() {
		return delegate;
	}
	public void setDelegate(FolderWatcherDelegate delegate) {
		this.delegate = delegate;
	}
	

}