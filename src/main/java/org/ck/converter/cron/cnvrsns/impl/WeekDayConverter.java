package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.ConversionFactory;

public class WeekDayConverter {

	public String applyConverterWithComma(String[] inputarray, String inputTimeZone) throws Exception {
		
		StringBuffer cnvrtdWeekDay = new StringBuffer();
        String[] tmp = inputarray;
        String[] days = inputarray[5].split(",");

        for (int i = 0; i < days.length; i++) {
            tmp[5] = days[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);

            if (i == 0) {
            	cnvrtdWeekDay.append(factory.getDaysOfWeek());
            } else {
            	cnvrtdWeekDay.append(",").append(factory.getDaysOfWeek());
            }
        }
    
		return cnvrtdWeekDay.toString();
	}
	
	public String applyConverstionWithHyphen(String[] inputarray, String inputTimeZone) throws Exception {
		
		StringBuffer cnvrtedWeekDay = new StringBuffer();
		

        String[] tmp = inputarray;
        String[] days = inputarray[5].split("-");
        for (int i = 0; i < days.length; i++) {
            tmp[5] = days[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);

            if (i == 0) {
            	cnvrtedWeekDay.append(factory.getDaysOfWeek());
            } else if (i == days.length - 1) {

                tmp[2] = getMaxNumberOutOfString(tmp[2]);
                tmpExp = buildExpressionFromArray(tmp);
                factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);

                cnvrtedWeekDay.append("-").append(factory.getDaysOfWeek() );
            } else {
            	cnvrtedWeekDay.append("-").append(factory.getDaysOfWeek() );
            }
        }
    
		
		return cnvrtedWeekDay.toString();
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
	
    public String getMaxNumberOutOfString(String input) {

        if ("*".equalsIgnoreCase(input) || "?".equalsIgnoreCase(input) || input.matches("[0-9]*")) {
            return input;
        } else if (input.indexOf("-") > 0) {
            String[] inar = input.split("-");
            return inar[1].trim();
        } else if (input.indexOf(",") > 0) {
            String[] inar = input.split(",");
            return inar[inar.length - 1].trim();
        } else {
        	return null;
        }
    }
}
