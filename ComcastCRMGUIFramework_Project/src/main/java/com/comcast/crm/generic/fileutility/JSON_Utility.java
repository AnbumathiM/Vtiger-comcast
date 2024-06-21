package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_Utility {
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader fileRead=new FileReader(".\\configAppData\\jsonCommondata.json");
		//step-01: parse JSON file into java object using JSONParse class
				JSONParser parser=new JSONParser();
				Object obj = parser.parse(fileRead);
				//step-02: convert JAVA object into JSONObject using downcast 
				JSONObject downcast=(JSONObject)obj;
				//step-03: get the value from json file using key
				String data=(String)downcast.get(key);
		return data;
		
	}

}
