package one;

public class MainClass {

	public static void main(String[] args) {

		while(true)
		{
			CopyFolder copyfold = new CopyFolder();
			copyfold.start();

			MonitorClass moncls = new MonitorClass();
			moncls.start();

		}
		/*
		 * while(true) { CopyFolder copyfold=new CopyFolder(); File
		 * dest_folder=copyfold.dircreate(); try { Thread.sleep(120000);; }
		 * catch (InterruptedException e) {
		 * 
		 * e.printStackTrace(); }
		 * 
		 * MonitorClass moncls=new MonitorClass();
		 * moncls.getFolderSize(dest_folder); }
		 */

	}
}
