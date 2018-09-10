package com.vocalink.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile 
{
	public static String read_testdata(String sprop) throws FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream(UIdata.startuppath+"//src//main//resources//TestData.properties"));
		String property = prop.getProperty(sprop);
		return property;
	}
}
