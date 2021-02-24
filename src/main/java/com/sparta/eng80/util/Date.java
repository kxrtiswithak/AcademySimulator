package com.sparta.eng80.util;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

public class Date {

	private BigInteger year;
	private short month;
	private short day;

	private Date(BigInteger year, short month, short day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	private Date(Date date) {
		this(date.year, date.month, date.day);
	}

	private Date(LocalDate localDate) {
		this(BigInteger.valueOf(localDate.getYear()),
				(short) localDate.getMonthValue(),
				(short) localDate.getDayOfMonth());
	}

	public Date plusYears(BigInteger years) {
		Date date;
		if (years.compareTo(BigInteger.ZERO) > 0) {
			BigInteger newYear = year.add(years);
			date = new Date(newYear, month, day);
		} else {
			date = new Date(this);
		}
		return date;
	}

	public Date plusMonths(BigInteger months) {
		Date date;
		if (months.compareTo(BigInteger.ZERO) > 0) {
			BigInteger yearsToAdd = months.divide(BigInteger.valueOf(12));
			BigInteger monthsToAdd = months.subtract(yearsToAdd.multiply(BigInteger.valueOf(12)));

			short newMonth = (short) ((month + monthsToAdd.intValue()) % 12);
			newMonth = newMonth == 0 ? 12 : newMonth;

			if (newMonth < month) yearsToAdd = yearsToAdd.add(BigInteger.ONE);
			BigInteger newYear = year.add(yearsToAdd);

			date = new Date(newYear, newMonth, day);
		} else {
			date = new Date(this);
		}
		return date;
	}

	public Date plusDays(BigInteger days) {
		Date date;
		if (days.compareTo(BigInteger.ZERO) > 0) {
			BigInteger yearsToAdd = days.divide(BigInteger.valueOf(365));
			BigInteger monthsToAdd = days.subtract(yearsToAdd.multiply(BigInteger.valueOf(365)))
					.divide(BigInteger.valueOf(30));
			BigInteger daysToAdd = days.subtract(monthsToAdd.multiply(BigInteger.valueOf(30)));

			short newDay = (short) ((day + daysToAdd.intValue() % 30));
			newDay = newDay == 0 ? 30 : newDay;

			if (newDay < day) monthsToAdd = monthsToAdd.add(BigInteger.ONE);
			short newMonth = (short) ((month + monthsToAdd.intValue()) % 12);
			newMonth = newMonth == 0 ? 12 : newMonth;

			if (newMonth < month) yearsToAdd = yearsToAdd.add(BigInteger.ONE);
			BigInteger newYear = year.add(yearsToAdd);

			date = new Date(newYear, newMonth, newDay);
		} else {
			date = new Date(this);
		}
		return date;
	}

	public boolean isBefore(Date otherDate) {
		if (year.compareTo(otherDate.year) < 0) {
			return true;
		} else if (year.compareTo(otherDate.year) > 0) {
			return false;
		}
		else if (month < otherDate.month) {
			return true;
		} else if (month > otherDate.month) {
			return false;
		} else if (day < otherDate.day) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAfter(Date otherDate) {
		if (year.compareTo(otherDate.year) > 0) {
			return true;
		} else if (year.compareTo(otherDate.year) < 0) {
			return false;
		}
		else if (month > otherDate.month) {
			return true;
		} else if (month < otherDate.month) {
			return false;
		} else if (day > otherDate.day) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEqual(Date otherDate) {
		return day == otherDate.day &&
				month == otherDate.month &&
				Objects.equals(year, otherDate.year);
	}


	public BigInteger getYear()
	{
		return year;
	}

	public short getMonth()
	{
		return month;
	}

	public short getDay()
	{
		return day;
	}

	public static Date now() {
		return new Date(LocalDate.now());
	}

	public static Date of(BigInteger year, short month, short day) {
		return new Date(year, month, day);
	}
}