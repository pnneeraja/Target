package one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class UpdateEcxelFile
{

	public String updatecell(String filePath, int col, int row, int value) throws IOException
	{

		FileInputStream fs = new FileInputStream(filePath);		//Access the workbook                  
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		//Access the worksheet, so that we can update / modify it. 
		HSSFSheet worksheet = wb.getSheet("Sheet1"); 
		// declare a Cell object
		Cell cell = null; 
		// Access the second cell in second row to update the value
		cell = worksheet.getRow(row).getCell(1);   
		// Get current cell value value and overwrite the value
		String new_value=Integer.toString(value);
		cell.setCellValue(new_value);
		//Close the InputStream  
		fs.close(); 
		//Open FileOutputStream to write updates
		FileOutputStream output_file =new FileOutputStream(filePath);  
		 //write changes
		wb.write(output_file);
		//close the stream
		output_file.close();
		return new_value;

	}

	
}
