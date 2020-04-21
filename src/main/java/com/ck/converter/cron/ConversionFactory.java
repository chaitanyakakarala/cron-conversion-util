package com.ck.converter.cron;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;

public class ConversionFactory {

    private final Date factoryDate;

    private final Calendar factoryCalender;

    private final String factorySeconds = "0";

    private String factoryDaysOfWeek = "?";

    private String factoryMinutes;

    private String factoryHours;

    private String factDaysofMonth;

    private String factMonths;

    private String factYears;
    
	public ConversionFactory(Date passedOnDate,String timeZoneRequired) {
		this.factoryDate = passedOnDate;
		
		this.factoryCalender = Calendar.getInstance(TimeZone.getTimeZone(timeZoneRequired));
		
		factoryCalender.setTime(factoryDate);

        this.factoryHours = String.valueOf(factoryCalender.get(Calendar.HOUR_OF_DAY));

        this.factoryMinutes = String.valueOf(factoryCalender.get(Calendar.MINUTE));

        this.factDaysofMonth = String.valueOf(factoryCalender.get(Calendar.DAY_OF_MONTH));

        this.factMonths = new SimpleDateFormat("MM").format(factoryCalender.getTime());

        this.factYears = String.valueOf(factoryCalender.get(Calendar.YEAR));

        this.factoryDaysOfWeek = String.valueOf(factoryCalender.get(Calendar.DAY_OF_WEEK));
	}


    public Date getDate() {
        return this.factoryDate;
    }


    public String getSeconds() {
        return this.factorySeconds;
    }


    public String getMins() {
        return this.factoryMinutes;
    }


    public String getDaysOfWeek() {
        String[] weekDays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        return weekDays[Integer.parseInt(this.factoryDaysOfWeek) - 1];
    }


    public String getHours() {
        return this.factoryHours;
    }


    public String getDaysOfMonth() {
        return this.factDaysofMonth;
    }


    public String getMonths() {
        return this.factMonths;
    }


    public String getYears() {
        return this.factYears;
    }
    
	public static ConversionFactory getConversionFactory(String expression, String timeZoneOfInExpr) throws Exception {

		CronExpression exp = new CronExpression(expression);
		exp.setTimeZone(TimeZone.getTimeZone(timeZoneOfInExpr));

		CronExpression.isValidExpression(expression);
		Date date = exp.getNextValidTimeAfter(new Date());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz");
		String dt = dateFormat.format(date);

		Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a,zzzz").parse(dt);
		return new ConversionFactory(cronDate,"GMT");

	}
	
}
