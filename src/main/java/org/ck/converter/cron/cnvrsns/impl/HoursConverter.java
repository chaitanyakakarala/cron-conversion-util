package org.ck.converter.cron.cnvrsns.impl;

import org.ck.converter.cron.ConversionFactory;

public class HoursConverter {
	
	short category = 0;
	
	public HoursConverter(String hrsStr) {
		if(hrsStr.indexOf(",") > 0) { 
			category = 1;
		} else if (hrsStr.indexOf("/") > 0) {
			category = 2;
		} else if (hrsStr.indexOf("-") > 0) {
			category = 3;
		} else if (hrsStr.matches("[0-9]*")) {
			category = 4;
		} else {
			category = 5;
		}
	}
	
	public String convert(String[] inputsExpAsArray, String inputTimeZone) {
		
		try {
			switch (category) {
			case 1:
				return convertMinutesWithComma(inputsExpAsArray, inputTimeZone);
			case 2:
				return convertHoursWithSlash(inputsExpAsArray, inputTimeZone);
			case 3:
				return convertHoursWithHyphen(inputsExpAsArray, inputTimeZone);
			case 4:
				
				return convertHoursNumeric(inputsExpAsArray, inputTimeZone);
			default:
				return convertHrsElse(inputsExpAsArray, inputTimeZone);
			}
			
		}catch(Exception exp) {
			System.err.println(exp.getMessage());
			throw new RuntimeException(exp.getMessage());
		}
		
	}

	public String convertMinutesWithComma(String[] inputsExpAsArray, String inputTimeZone) throws Exception {
		
		StringBuffer cnvrtdMinsWthComma = new StringBuffer();
        String[] tmp = inputsExpAsArray;
        String[] hours = inputsExpAsArray[2].split(",");
        
        for (int i = 0; i < hours.length; i++) {
            tmp[2] = hours[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	cnvrtdMinsWthComma.append(factory.getHours());
            } else {
            	cnvrtdMinsWthComma.append(",").append(factory.getHours());
            }
        }
        
		return cnvrtdMinsWthComma.toString();    
	}

	public String convertHoursWithSlash(String[] inputsExpAsArray, String inputTimeZone) throws Exception {

        StringBuffer convertedMinsWithSlash = new StringBuffer();
        String[] tmp = inputsExpAsArray;
        String[] mins = inputsExpAsArray[2].split("/");
        
        for (int i = 0; i < mins.length; i++) {
            tmp[2] = mins[i];
            if (i == 1) {
            	convertedMinsWithSlash.append( "/" ).append(tmp[i]).append(" ");
                continue;
            }
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	convertedMinsWithSlash.append(" ").append(factory.getHours());
            } else if (i == mins.length - 1) {
            	convertedMinsWithSlash.append("/").append(factory.getHours()).append(" ");
            } else {
            	convertedMinsWithSlash.append("/").append(factory.getHours());
            }
        }
        return convertedMinsWithSlash.toString();
    
	}
    
	public String convertHoursWithHyphen(String[] inputsExpAsArray, String inputTimeZone)throws Exception {

		StringBuffer convertedHours = new StringBuffer();
        String[] tmp = inputsExpAsArray;
        String[] hrs = inputsExpAsArray[2].split("-");
        
        for (int i = 0; i < hrs.length; i++) {
            tmp[2] = hrs[i];
            String tmpExp = buildExpressionFromArray(tmp);
            ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
            if (i == 0) {
            	convertedHours.append(factory.getHours());
            } else {
            	convertedHours.append("-").append(factory.getHours());
            }
        }

        return convertedHours.toString();
	}
	
	
	public String convertHoursNumeric(String[] inputsExpAsArray, String inputTimeZone) throws Exception {

		String tmpExp = buildExpressionFromArray(inputsExpAsArray);
        ConversionFactory factory = ConversionFactory.getConversionFactory(tmpExp, inputTimeZone);
        return factory.getHours();
	}
	
	public String convertHrsElse(String[] inputsExpAsArray, String inputTimeZone) {
		
		return inputsExpAsArray[2];
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
