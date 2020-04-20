package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.ConversionFactory;

public class DayOfMonthConverter {

    short category = 0;

    public DayOfMonthConverter(String daysOfMonthStr) {
        if ("*".equals(daysOfMonthStr) || "?".equalsIgnoreCase(daysOfMonthStr)) {
            category = 0;
        } else if (daysOfMonthStr.contains("/")) {
            category = 1;
        } else if (daysOfMonthStr.contains("-")) {
            category = 2;
        }
    }

    public String convert(String[] inputsExpAsArray, String inputTimeZone)  {

        try {
            switch (category) {
                case 1:
                    return conversion4rSlashSeparation(inputsExpAsArray, inputTimeZone);
                case 2:
                    return conversion4rHyphenSeparation(inputsExpAsArray, inputTimeZone);
                default:
                    return withoutConversion(inputsExpAsArray, inputTimeZone);
            }
        }catch(Exception exp) {

            System.err.println(exp.getMessage());
            throw new RuntimeException(exp);
        }

    }

    public String withoutConversion(String[] inar, String timeZone) {
        return inar[3];
    }


	public String conversion4rHyphenSeparation(String[] inar, String inputTimeZone) throws Exception {
		
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
	
	public String conversion4rSlashSeparation(String[] inputarray, String inputTimeZone) throws Exception {

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
