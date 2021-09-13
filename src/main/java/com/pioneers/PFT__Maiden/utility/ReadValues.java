package com.pioneers.PFT__Maiden.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:config.properties")
public class ReadValues {
	
	@Value("${theme:Hello World}")
	String editionTheme;
	
	final String var = "Variable value";
	
	public String getEdition() {
		return editionTheme;
	}
	
	public String getVar() {
		return var;
	}

}
