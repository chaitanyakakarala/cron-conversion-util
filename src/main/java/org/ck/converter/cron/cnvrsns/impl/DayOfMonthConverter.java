package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.ConversionFactory;

public class DayOfMonthConverter {

	public String applyConversionWithHyphen(String[] inar, String inputTimeZone) throws Exception {
		
		StringBuffer monthOfDay = new StringBuffer();
        String[] tmp = inar;
        String[] days = inar[3].split("-"); 

        for (int i = 0; i < days.length; i++) {
            tmp[3] = days[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);

            if (i == 0) {
            	monthOfDay.append(factory.getDaysOfMonth());
            }  else {
            	monthOfDay.append("-").append(factory.getDaysOfMonth());
            }
        }

        return monthOfDay.toString();
	}
	
	public String applyConversionWithSlash(String[] inputarray, String inputTimeZone) throws Exception {

		StringBuffer monthOfDay = new StringBuffer();
        String[] tmp = inputarray;
        String[] days = inputarray[3].split("/"); // if - is provided ... get them from calander object

        for (int i = 0; i < days.length; i++) {
            tmp[3] = days[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);

            if (i == 0) {
            	monthOfDay.append(factory.getDaysOfMonth());
            } else if (i == days.length - 1) {
            	monthOfDay.append("/").append(factory.getDaysOfMonth());
            } else {
            	monthOfDay.append("/").append(factory.getDaysOfMonth());
            }
        }

		return monthOfDay.toString();
	}
	
	public String convertMinsElse(String[] inputsExpAsArray, String inputTimeZone) {
		
		return inputsExpAsArray[3];
	}
	
	private static String buildExpressionFromArray(String[] ar) {
        String exp = "";

        for (int i = 0; i < ar.length; i++) {
            if (i != ar.length - 1) {
                exp = exp + ar[i] + " ";
            } else {
                exp = exp + ar[i];
            }
        }

        return exp;
    }
	
}
