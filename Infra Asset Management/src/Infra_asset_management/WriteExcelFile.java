package one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class WriteExcelFile
{
	public void writetoexcel(String filePath, long employe_id, String asset_name) throws IOException, RowsExceededException, WriteException, BiffException
	{

		Label label;
		String FilePath = "D:\\Documents\\Employe_Details.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);

		// TO get the access to the sheet
		Sheet sh = wb.getSheet("Sheet1");

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getColumns();
		
		File fileToEdit=new File(FilePath);
		Workbook existingWorkbook = Workbook.getWorkbook(new File(fileToEdit.getAbsolutePath()));
		//WritableWorkbook workbookCopy = Workbook.createWorkbook(new File(FilePath), existingWorkbook);
		Sheet sheetToEdit = existingWorkbook.getSheet("Sheet1");
		WritableCell cell;
		
		for (int row = 1; row < totalNoOfRows; row++) 
		{
			for(int col=0;col<totalNoOfCols;col++)
			{
				if((sh.getCell(col, row).getContents()).equalsIgnoreCase(asset_name))
				{
					continue;
				}
				else
				{
					Label l = new Label(col, row, "assigned");
					cell = (WritableCell) l;
					sheetToEdit.add(cell);
					existingWorkbook.write();
					existingWorkbook.close();
					 existingWorkbook.close();
				}

			}
		}
	}

}
