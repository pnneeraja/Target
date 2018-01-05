package one;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.folder.health.monitor.MostRecentFiles;

public class MonitorClass extends Thread {
	public void run() {
		

		File dest_folder = new File("D:\\Documents\\secured");
		long folder_length = getFolderSize(dest_folder);
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		long fileSizeInKB = folder_length / 1024;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		long fileSizeInMB = fileSizeInKB / 1024;
		File[] fList = dest_folder.listFiles();

		while (fileSizeInMB > 100) {
			long numDays = 1; // this needs to be a long.

			// WARNING! OLD FILES IN THIS DIRECTORY WILL BE DELETED.
			// String dir = "D:\\Documents\\secured";
			
			File new_destFolder = new File("D:\\Documents\\archive");
			new_destFolder.mkdir();
			// IF YOU ACCIDENTALLY POINT THIS TO C:\\Windows or other sensitive
			// directory (and run it) you will be living in the house of pain.

			// File directory = new File(dir);

			if (fList != null) {
				for (File file : fList) {
					if (file.isFile()) {

						long diff = new Date().getTime() - file.lastModified();
						long cutoff = (numDays * (24 * 60 * 60 * 1000));

						if (diff > cutoff) {
							CopyFolder copyfolder = new CopyFolder();
							try {
								copyfolder.copyFolder(file, new_destFolder);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							System.out.println("Archived file:" + file.getName());
							file.delete();

						}
						MostRecentFiles mrf = new MostRecentFiles();
						String mostrecent_files = mrf.mostrecentfilesindirectory(dest_folder);
						System.out.println("Recently updated file:" + mostrecent_files);
					}
					folder_length = getFolderSize(dest_folder);
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					fileSizeInKB = folder_length / 1024;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					fileSizeInMB = fileSizeInKB / 1024;
					System.out.println("Current size of Folder in MB" + fileSizeInMB);

					if (fileSizeInMB > 100) {
						continue;
					}
				}
			}

		}
		getFolderSize(dest_folder);
		folder_length = getFolderSize(dest_folder);
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		fileSizeInKB = folder_length / 1024;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		fileSizeInMB = fileSizeInKB / 1024;
		System.out.println("Current size of Folder in MB" + fileSizeInMB);
		MostRecentFiles mrf = new MostRecentFiles();
		String mostrecent_files = mrf.mostrecentfilesindirectory(dest_folder);
		System.out.println("Recently updated file:" + mostrecent_files);
		for (File file : fList) {
			if (file.isFile()) {
				System.out.println(file+" Deleted:"+file.exists());
			}
		}

		try {
			Thread.sleep(300000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public long getFolderSize(File folder) {
		long length = 0;
		File[] files = folder.listFiles();
		if (files == null || files.length == 0) {
			return length;
		}
		for (File file : files) {
			if (file.isFile()) {
				length += file.length();
			} else {
				length += getFolderSize(file);
			}
		}
		return length;
		
		
	}
	
}
