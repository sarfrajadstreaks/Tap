package com.autoData.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class saveFile {
	public static void write(ArrayList<String> setting,ArrayList<String> tabs,ArrayList<String> tabsback,ArrayList<String> components,String filename) throws IOException
	{
		PrintWriter writer=new PrintWriter(filename+".tap");
		writer.println("839711410211497106");
		writer.println(setting);
		writer.println(tabs);
		writer.println(components);
		writer.println(tabsback);
		writer.close();
	}
	public static String[] getFileDetail(String filename){
		filename=filename.replace("\\","/");
		//System.out.println(filename);
		String filedetail[]=filename.split("/");
		String filePath = "";
		String fileDetail[] = new String[3];
		for(int i=0;i<filedetail.length-2;i++){
			filePath=filePath+filedetail[i]+"/";
		}
		String FileName=filedetail[filedetail.length-1];
		String absFilename=FileName.substring(0,FileName.length()-5);
		String FileExt=FileName.substring(FileName.length()-3);
		//System.out.println(filePath);
		//System.out.println(absFilename);
		//System.out.println(FileExt);
		fileDetail[0]=filePath;
		fileDetail[1]=absFilename;
		fileDetail[2]=FileExt;
		return fileDetail;
	}
}
