package com.ck.converter.cron.cnvrsns.impl;

import com.ck.converter.cron.ConversionFactory;

public class MinutesConverter {

	short category = 0;
	
	public MinutesConverter(String inMinStr) {
		if(inMinStr.indexOf(",") > 0) { 
			category = 1;
		} else if (inMinStr.indexOf("/") > 0) {
			category = 2;
		} else if (inMinStr.matches("[0-9]*")) {
			category = 3;
		} else {
			category = 4;
		}
	}
	
	public String convert(String[] inputsExpAsArray, String inputTimeZone)  {
		
		try {
			switch (category) {
			case 1:
				return convertMinutesWithComma(inputsExpAsArray, inputTimeZone);
			case 2:
				return convertMinutesWithSlash(inputsExpAsArray, inputTimeZone);
			case 3:
				return convertMinutesNumeric(inputsExpAsArray, inputTimeZone);
			default:
				return convertMinsElse(inputsExpAsArray, inputTimeZone);
			}
		}catch(Exception exp) {
			
			System.err.println(exp.getMessage());
			throw new RuntimeException(exp);
		}
		
	}
	
	protected String convertMinutesWithComma(String[] inputsExpAsArray, String inputTimeZone) throws Exception {
		
		StringBuffer cnvrtdMinsWthComma = new StringBuffer();

        String[] tmp = inputsExpAsArray;

        String[] mins = inputsExpAsArray[1].split(",");
        
        for (int i = 0; i < mins.length; i++) {
            tmp[1] = mins[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	cnvrtdMinsWthComma.append(factory.getMins());
            } else {
            	cnvrtdMinsWthComma.append(",").append(factory.getMins());
            }
        }
        
		return cnvrtdMinsWthComma.toString();    
	}
	
	
	protected String convertMinutesWithSlash(String[] inputsExpAsArray, String inputTimeZone) throws Exception {

        StringBuffer convertedMinsWithSlash = new StringBuffer();

        String[] tmp = inputsExpAsArray;

        String[] mins = inputsExpAsArray[1].split("/");
        
        for (int i = 0; i < mins.length; i++) {
            tmp[1] = mins[i];
            //Slash is a special case when it comes to cron...if '/' is there
			//it means there are only two numbers which says to run both hours.
			//Hence it can be stop conversion after 2nd item
            if (i == 1) {
            	convertedMinsWithSlash.append( "/" ).append( tmp[i] ).append(" ");
                continue;
            }
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	convertedMinsWithSlash.append(" ").append( factory.getMins());
            } else if (i == mins.length - 1) {
            	convertedMinsWithSlash.append("/").append(factory.getMins()).append(" ");
            } else {
            	convertedMinsWithSlash.append("/").append(factory.getMins());
            }
        }
        return convertedMinsWithSlash.toString();
    
	}
	
	protected String convertMinutesNumeric(String[] inputsExpAsArray, String inputTimeZone) throws Exception {

		String tmpExp = buildExpressionFromArray(inputsExpAsArray);
        ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
        return factory.getMins();
	}

	protected String convertMinsElse(String[] inputsExpAsArray, String inputTimeZone) {
		
		return inputsExpAsArray[1];
	}
	
    protected static String buildExpressionFromArray(String[] ar) {
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
