package com.ck.converter.cron.cnvrsns.impl;

import com.ck.converter.cron.ConversionFactory;

public class WeekDayConverter {

    short category = 0;

    public WeekDayConverter(String dayOfWeek) {
        if ("*".equals(dayOfWeek) || "?".equalsIgnoreCase(dayOfWeek)) {
            category = 0;
        } else if (dayOfWeek.contains("-")) {
            category = 1;
        } else if (dayOfWeek.contains(",")) {
            category = 2;
        }
    }

    public String convert(String[] inputsExpAsArray, String inputTimeZone)  {

        try {
            switch (category) {
                case 1:
                    return applyConverstionWithHyphen(inputsExpAsArray, inputTimeZone);
                case 2:
                    return applyConverterWithComma(inputsExpAsArray, inputTimeZone);
                default:
                    return withoutConversion(inputsExpAsArray, inputTimeZone);
            }
        }catch(Exception exp) {

            System.err.println(exp.getMessage());
            throw new RuntimeException(exp);
        }

    }

    public String withoutConversion(String[] inputarray, String inputTimeZone) throws Exception{
        if (!"*".equals(inputarray[5]) && !"?".equals(inputarray[5])) {
            return withConversionForSingleItem(inputarray,inputTimeZone);
        }
        return inputarray[5];
    }

    public String withConversionForSingleItem(String[] inputarray, String inputTimeZone) throws Exception{
        StringBuffer cnvrtdWeekDay = new StringBuffer();
        String[] tmp = inputarray;
        String[] days = inputarray[5].split(",");

        tmp[5] = inputarray[5];
        String tmpExp = buildExpressionFromArray(tmp);
        ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
        cnvrtdWeekDay.append(factory.getDaysOfWeek());

        return cnvrtdWeekDay.toString();
    }

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
