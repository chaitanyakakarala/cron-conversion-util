package com.ck.converter.cron.cnvrsns;

public interface Converter {

	String convertSeconds(String[] inar, String inputTimeZone);
	
	String convertMinutes(String[] inar, String inputTimeZone);
	
	String convertHours(String[] inar, String inputTimeZone);
	
	String convertDaysOfMonth(String[] inar, String inputTimeZone);
	
	String convertWeekDayOfMonth(String[] inar, String inputTimeZone);
	
	String convertMonth(String[] inar, String inputTimeZone);
	
}
