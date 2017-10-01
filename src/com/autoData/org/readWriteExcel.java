package com.autoData.org;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readWriteExcel {
	
	@SuppressWarnings("unused")
	public void readWriteXLS(String filewithpath,String sheetToUpdate,ArrayList<String>header,ArrayList<String>value){
		try {
			
			FileInputStream file = null;
			boolean fileExist=false;
			try {
				file = new FileInputStream(new File(filewithpath));
				fileExist=true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fileExist=false;
			}
			HSSFWorkbook workbook;
			HSSFSheet sheet;
			Cell cell = null;
			if(fileExist){
				workbook = new HSSFWorkbook(file);
				CellStyle style = workbook.createCellStyle();//Create style
			    HSSFFont font = workbook.createFont();//Create font
			    font.setBold(true);
			    style.setFont(font);//set it to bold
				sheet= workbook.getSheet(sheetToUpdate);
				if(sheet==null){
					sheet= workbook.createSheet(sheetToUpdate);
					sheet.createRow(0);
					for(int i=0;i<header.size();i++){
						cell = sheet.getRow(0).createCell(i);
						cell.setCellValue(header.get(i));
						cell.setCellStyle(style);
					}
					int lasrow=sheet.getLastRowNum();
					sheet.createRow(lasrow+1);
					for(int i=0;i<value.size();i++){
						cell = sheet.getRow(lasrow+1).createCell(i);
						cell.setCellValue(value.get(i));
						//cell.setCellStyle(style);
					}
				}
				else{
					sheet.createRow(0);
					for(int i=0;i<header.size();i++){
						cell = sheet.getRow(0).createCell(i);
						cell.setCellValue(header.get(i));
						cell.setCellStyle(style);
					}
					int lasrow=sheet.getLastRowNum();
					sheet.createRow(lasrow+1);
					for(int i=0;i<value.size();i++){
						cell = sheet.getRow(lasrow+1).createCell(i);
						cell.setCellValue(value.get(i));
					}
				}
				file.close();
			}
			else{
				workbook = new HSSFWorkbook();
				CellStyle style = workbook.createCellStyle();//Create style
			    HSSFFont font = workbook.createFont();//Create font
			    font.setBold(true);
			    style.setFont(font);//set it to bold
				sheet= workbook.createSheet(sheetToUpdate);
				for(int i=0;i<header.size();i++){
					cell = sheet.getRow(0).createCell(i);
					cell.setCellValue(header.get(i));
					cell.setCellStyle(style);
				}
				
			}
			
			
					
			
			
			FileOutputStream outFile =new FileOutputStream(new File(filewithpath));
			workbook.write(outFile);
			outFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public  void readWriteXLSX(String filewithpath,String sheetToUpdate,ArrayList<String>header,ArrayList<String>value) throws IOException{
	
			FileInputStream file = null;
			boolean fileExist=false;
			try {
				file = new FileInputStream(new File(filewithpath));
				fileExist=true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				fileExist=false;
			}

			XSSFWorkbook workbook;
			XSSFSheet sheet;
			Cell cell = null;
			
			if(fileExist){
				workbook = new XSSFWorkbook(file);
				CellStyle style = workbook.createCellStyle();//Create style
			    XSSFFont font = workbook.createFont();//Create font
			    font.setBold(true);
			    style.setFont(font);//set it to bold
			    
				sheet= workbook.getSheet(sheetToUpdate);
				if(sheet==null){
					sheet= workbook.createSheet(sheetToUpdate);
					sheet.createRow(0);
					for(int i=0;i<header.size();i++){
						cell = sheet.getRow(0).createCell(i);
						cell.setCellValue(header.get(i));
						cell.setCellStyle(style);
					}
					int lasrow=sheet.getLastRowNum();
					sheet.createRow(lasrow+1);
					for(int i=0;i<value.size();i++){
						cell = sheet.getRow(lasrow+1).createCell(i);
						cell.setCellValue(value.get(i));
						//cell.setCellStyle(style);
					}
				}
				else{
					sheet.createRow(0);
					for(int i=0;i<header.size();i++){
						cell = sheet.getRow(0).createCell(i);
						cell.setCellValue(header.get(i));
						cell.setCellStyle(style);
					}
					int lasrow=sheet.getLastRowNum();
					sheet.createRow(lasrow+1);
					for(int i=0;i<value.size();i++){
						cell = sheet.getRow(lasrow+1).createCell(i);
						cell.setCellValue(value.get(i));
					}
					
				}
				file.close();
			}
			else{
				workbook = new XSSFWorkbook();
				CellStyle style = workbook.createCellStyle();//Create style
			    XSSFFont font = workbook.createFont();//Create font
			    font.setBold(true);
			    style.setFont(font);//set it to bold
				sheet= workbook.createSheet(sheetToUpdate);
				sheet.createRow(0);
				for(int i=0;i<header.size();i++){
					cell = sheet.getRow(0).createCell(i);
					cell.setCellValue(header.get(i));
					cell.setCellStyle(style);
				}
				int lasrow=sheet.getLastRowNum();
				sheet.createRow(lasrow+1);
				for(int i=0;i<value.size();i++){
					cell = sheet.getRow(lasrow+1).createCell(i);
					cell.setCellValue(value.get(i));
				}
			}
			
			FileOutputStream outFile =new FileOutputStream(new File(filewithpath));
			workbook.write(outFile);
			outFile.close();
			
		
	}
}
