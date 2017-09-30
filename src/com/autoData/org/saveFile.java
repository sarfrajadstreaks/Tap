package com.autoData.org;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class saveFile {
	public static void write(ArrayList<String> setting,ArrayList<String> tabs,ArrayList<String> tabsback,ArrayList<String> components,String filename) throws IOException
	{
		PrintWriter writer=new PrintWriter(filename);
		writer.println("839711410211497106");
		writer.println(setting);
		writer.println(tabs);
		writer.println(components);
		writer.println(tabsback);
		writer.close();
	}
}
