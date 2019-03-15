package pt.iscte.sid.projeto.TEMP;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFile {	
	
	private ArrayList<ExcelObject> procList = new ArrayList<>();

	public void reading() {
		try	{
			FileInputStream file = new FileInputStream(new File("excelFile/cris_final.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			
			
			for(int i = sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum();i++) {
				ExcelObject e = new ExcelObject();
				Row ro = sheet.getRow(i);
				for(int j = ro.getFirstCellNum(); j<=ro.getLastCellNum();j++) {
					Cell ce = ro.getCell(j);
					/*
					 * if(j==0) e.setAno((int)ce.getNumericCellValue()); if(j==1)
					 * e.setNproc(ce.getStringCellValue()); if(j==2)
					 * e.setNemp((int)ce.getNumericCellValue()); if(j==3)
					 * e.setEmp(ce.getStringCellValue()); if(j==4)
					 * e.setValor(ce.getStringCellValue()); if(j==5)
					 * e.setData(ce.getStringCellValue()); if(j==6)
					 * e.setServ(ce.getStringCellValue()); if(j==7)
					 * e.setNserv((int)ce.getNumericCellValue());
					 */
					
				}
				procList.add(e);
			}
			
			/*WordGenerator n = new WordGenerator();
			for(ExcelObject proc: procList)				
				n.CreateDoc(proc.getNproc(), proc.getValor(), proc.getEmp(), proc.getNemp());	*/
			file.close();
			workbook.close();
			
			System.out.println(procList.size());			
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	

	}

	public ArrayList<ExcelObject> getProcList() {
		return procList;
	}
	
	
}
