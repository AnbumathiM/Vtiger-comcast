package com.comcast.cr.generic.webdriverutility;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random=new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}
	public String getSystemDateYYYYMMDD(){
		Date dateObj=new Date();
		SimpleDateFormat simDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String date = simDateFormat.format(dateObj);
		return date;
	}
	public String getRequiredDateYYYYDDMM(int days) {
		//simpledateformat we can format our date at what format we require
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//accessing calender to get before and after date, month and year
			Calendar calendar =Calendar.getInstance() ;  
			calendar.add(Calendar.DAY_OF_MONTH,days); 
			String requireDate=dateFormat. format(calendar.getTime());
		  return requireDate;
		   
	}
}
