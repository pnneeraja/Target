package one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyFolder extends Thread {
	public void run() {
		dircreate();
		System.out.println("Done");
		try {
			Thread.sleep(120000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File dircreate() {
		File srcFolder = new File("D:\\Documents\\temp");
		File destFolder = new File("D:\\Documents\\secured");
		destFolder.mkdir();
		try {
			copyFolder(srcFolder, destFolder);
		} catch (IOException ex) {
			ex.printStackTrace();
			// error, just exit
			System.exit(0);

		}

		return destFolder;

	}

	public void copyFolder(File src, File dest) throws IOException {
		if (src.isDirectory()) {
			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
			}

			// list all the directory contents
			String[] files = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			copyfiless(src, dest);
		}
	}

	public void copyfiless(File src, File dest) throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);

		byte[] buffer = new byte[1024];

		int length;
		// copy the file content in bytes
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}

		in.close();
		out.close();

		Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

}
