package com.autoData.org;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readFile {
	public static String readit(String filenm) throws IOException{
		File file=new File(filenm.replace("\\","/"));
		FileReader reader = null;
		String line = null;
		
		reader = new FileReader(file);
		BufferedReader bf=new BufferedReader(reader);
		StringBuffer strbf=new StringBuffer();
		try {
				while((line=bf.readLine()) != null){
					strbf.append(line);
					strbf.append("\n");
					/*System.out.println(line);
					return line;*/
				}
				reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strbf.toString();
	}

}
