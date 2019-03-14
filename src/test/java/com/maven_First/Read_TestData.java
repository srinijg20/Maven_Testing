package com.maven_First;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_TestData 
{
XSSFWorkbook wb;
XSSFSheet sh1;
int TestRow;
int TestValue;
int Col;
int row;

public Read_TestData(String sPath) throws Exception
{
	try {
		File src=new File(sPath);
		FileInputStream Fis=new FileInputStream(src);
		wb=new XSSFWorkbook(Fis);
	} 
	catch (Exception e) 
	{
		System.out.println(e.getMessage());
	}
}

public Object fnReadData(String Sheet,String TC,Object Key)
{
	sh1=wb.getSheet(Sheet);
	row=sh1.getLastRowNum();
	row=row+1;
	HashMap<Object,Object> obj1=new HashMap<Object,Object>();
	
	for (int i=0;i<row;i++)
	{
		String Row1=sh1.getRow(i).getCell(0).getStringCellValue();
		if(Row1.equalsIgnoreCase(TC) )
		{
			TestRow=i+1;
			TestValue=i+2;
		    Col=sh1.getRow(TestRow).getLastCellNum();
			//System.out.println("The test case is found at "+i+" and the data row is "+TestRow);
			//System.out.println("The total number of columns present in row "+TestRow+ " is "+Col);
			break;
		}
	}
	
	for (int j=0;j<Col;j++)
	{
	//obj1.put(sh1.getRow(TestRow).getCell(j).getCellType(), sh1.getRow(TestValue).getCell(j).getStringCellValue());
		Object Key1=printCellValue(sh1.getRow(TestRow).getCell(j));
		Object Value1=printCellValue(sh1.getRow(TestValue).getCell(j));
		
	obj1.put(Key1, Value1);
	}
	try {
		wb.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(obj1);
	Object x=obj1.get(Key);
	x=((String) x).trim();
	//System.out.println(x);
	return x;
	
}
private static Object printCellValue(Cell cell) {
	Object objret = null;
    switch (cell.getCellType()) {
        case BOOLEAN:
            objret=cell.getBooleanCellValue();
            break;
        case STRING:
        	objret=cell.getStringCellValue();
            break;
        case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
            	objret=cell.getDateCellValue();
            } else {
            	objret=cell.getNumericCellValue();
            }
            break;
        case FORMULA:
        	objret=cell.getCellFormula();
            break;
        case BLANK:
            System.out.print("");
            break;
        default:
            System.out.print("");
    }

    System.out.print("\t");
	return objret;
}
}
