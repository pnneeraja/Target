package one;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ReadExcelFile 
{
    static WritableWorkbook wwbCopy;
    UpdateEcxelFile upex=new UpdateEcxelFile();
    WriteExcelFile wrex=new WriteExcelFile();

	public void readExcel(String[] assets, long employe_id) throws BiffException, IOException, WriteException
	{
		String status = null;
		String FilePath = "D:\\Documents\\Inventory_Details.xls";
		FileInputStream fs = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fs);

		// TO get the access to the sheet
		Sheet sh = wb.getSheet("Sheet1");

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getColumns();

		for(int i=0;i<assets.length;i++)
		{
		for (int row = 1; row < totalNoOfRows; row++) {

			int col = 0;
			do
			{
				//System.out.print(sh.getCell(col, row).getContents() + "\t");
				
				if((sh.getCell(col, row).getContents()).equalsIgnoreCase(assets[i]))
				{
					String inventory=sh.getCell(col, row).getContents();
					String value = sh.getCell(col+1, row).getContents();
					System.out.println("Avaliable "+inventory+":"+value);
					int result = Integer.parseInt(value);

					if(result>0) 
					{
						status= inventory+" assigned to employe-"+employe_id;
						System.out.println(status);
						col++;
					
						String new_value=upex.updatecell(FilePath,col+1,row,result-1);
						System.out.println("After assigning to employe-"+employe_id+" Remaining "+inventory+"-"+new_value);
						
						wrex.writetoexcel(FilePath,employe_id,assets[i].toString());
					}
					else
					{
						status= inventory+" not assigned";
						System.out.println(status);
						col++;
					}

				}
				else
				{
					col++;
				}
			}while(col<totalNoOfCols);
				
		}
		}
		

	}

	/*private void updatecell(int col, int row, int value) throws BiffException, IOException, WriteException 
	{
		//Workbook workbook = Workbook.getWorkbook(new File(FilePath));
		//WritableWorkbook copy = Workbook.createWorkbook(new File(FilePath), workbook);
        WritableSheet wshTemp = wwbCopy.getSheet("Sheet1");
        String string_value=Integer.toString(value);
        Label labTemp = new Label(col, row, string_value);
                
        try {
            wshTemp.addCell(labTemp);
             } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }



	}*/

	public static void main(String args[]) throws BiffException, IOException, WriteException
	{

		long employe_id=209021;
		String[] assets= {"laptop","monitor"};
		ReadExcelFile DT = new ReadExcelFile();
		DT.readExcel(assets,employe_id);
	}
}
