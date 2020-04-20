package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.cnvrsns.Converter;

public class ConverterImpl implements Converter{

	@Override
	public String convertSeconds(String[] inar,String timeZone) {
		
		return inar[0];
	}

	@Override
	public String convertMinutes(String[] inar, String inputTimeZone) {
		
		return new MinutesConverter(inar[1]).convert(inar, inputTimeZone);
	}

	@Override
	public String convertHours(String[] inar, String inputTimeZone) {
		
		return new HoursConverter(inar[2]).convert(inar, inputTimeZone);
	}

	@Override
	public String convertDaysOfMonth(String[] inar, String inputTimeZone) {
		
		return new DayOfMonthConverter(inar[3]).convert(inar, inputTimeZone);
	}

	@Override
	public String convertWeekDayOfMonth(String[] inar, String inputTimeZone) {
		
		return null;
	}

	@Override
	public String convertMonth(String[] inar, String inputTimeZone) {

		return inar[4];
	}

}
