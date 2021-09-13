package com.pioneers.PFT__Maiden.utility;

import java.io.*;
import java.util.*;


public class PropertyAccessor {
	
	public static String getPropValue() throws Exception {
		
		FileReader reader = new FileReader("config.properties");
		
		Properties props = new Properties();
		props.load(reader);
		
		return props.getProperty("edition");
	}

}
