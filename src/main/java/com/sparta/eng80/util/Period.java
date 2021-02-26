package com.sparta.eng80.util;

import java.math.BigInteger;

public class Period {

	private BigInteger years;
	private short months;
	private short days;

	private Period(BigInteger years, short months, short days) {
		this.years = years;
		this.months = months;
		this.days = days;
	}

	public BigInteger getYears()
	{
		return years;
	}

	public short getMonths()
	{
		return months;
	}

	public short getDays()
	{
		return days;
	}

	public static Period between(Date startDate, Date endDate) {
		BigInteger years = endDate.getYear().subtract(startDate.getYear());
		short months;
		if (startDate.getMonth() > endDate.getMonth()) {
			months = (short) (12 - startDate.getMonth() + endDate.getMonth());
			years.add(BigInteger.ONE);
		} else {
			months = (short) (endDate.getMonth() - startDate.getMonth());
		}
		short days;
		if (startDate.getDay() > endDate.getDay()) {
			days = (short) (30 - startDate.getDay() + endDate.getDay());
			months++;
		} else {
			days = (short) (endDate.getDay() - startDate.getDay());
		}
		return new Period(years, months, days);
	}
}
