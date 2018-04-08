package org.ck.converter.cron;

import org.ck.converter.cron.cnvrsns.Converter;
import org.ck.converter.cron.cnvrsns.impl.ConverterImpl;

public class DemoApplication {
	
	private static final String INPUT = "1 1 * ? * MON-FRI";

	public static void main(String[] args) {
		Converter cnvrtr = new ConverterImpl();
		String seconds = cnvrtr.convertSeconds(INPUT.split(" "),"IST");		
		String minutes = cnvrtr.convertMinutes(INPUT.split(" "), "IST");
		String hours = cnvrtr.convertHours(INPUT.split(" "), "IST");
		String dayOfMonth = cnvrtr.convertMinutes(INPUT.split(" "), "IST");
		String month = cnvrtr.convertMinutes(INPUT.split(" "), "IST");
		String dayOfWeek = cnvrtr.convertMinutes(INPUT.split(" "), "IST");
		
		System.out.println(String.format("%s,%s,%s,%s,%s,%s", seconds,minutes,hours,dayOfMonth,month,dayOfWeek));
		
		
	}
}
