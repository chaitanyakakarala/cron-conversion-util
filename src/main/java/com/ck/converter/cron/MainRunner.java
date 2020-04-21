package com.ck.converter.cron;

import com.ck.converter.cron.cnvrsns.Converter;
import com.ck.converter.cron.cnvrsns.impl.ConverterImpl;
import org.quartz.CronExpression;

public class MainRunner {
	
	private static final String INPUT = "1 30 1 1-20 * ?";

	public static void main(String[] args) throws Exception{
		Converter cnvrtr = new ConverterImpl();
		String seconds = cnvrtr.convertSeconds(INPUT.split(" "),"IST");		
		String minutes = cnvrtr.convertMinutes(INPUT.split(" "), "IST");
		String hours = cnvrtr.convertHours(INPUT.split(" "), "IST");
		String dayOfMonth = cnvrtr.convertDaysOfMonth(INPUT.split(" "), "IST");
		String month = cnvrtr.convertMonth(INPUT.split(" "), "IST");
		String dayOfWeek = cnvrtr.convertWeekDayOfMonth(INPUT.split(" "), "IST");

		String outCron = String.format("%s %s %s %s %s %s", seconds,minutes,hours,dayOfMonth,month,dayOfWeek);

		System.out.println(outCron);
		System.out.println(CronExpression.isValidExpression(outCron));

	}
}
