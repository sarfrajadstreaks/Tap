package com.autoData.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readWriteExcel {
	
	public void readWriteXLS(){
		
	}
	
	public  void readWriteXLSX(){
		
	}
	
	public  boolean isFilePresent(String fileWithPath){
		try {
			 FileInputStream file= new FileInputStream(new File(fileWithPath));
			return true;
		} catch (FileNotFoundException e) {
			
			return false;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//readWriteExcel.readWriteXLSX();
		readWriteExcel obj=new readWriteExcel();	
		if (obj.isFilePresent("F:\\sss.xlsx")){
			
		}
		
    }
}
