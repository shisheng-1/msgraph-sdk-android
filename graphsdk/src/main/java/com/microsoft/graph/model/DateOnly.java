package com.microsoft.graph.model;


import java.text.ParseException;
import java.util.Locale;

/**
 * A timezone-nonspecific date
 */
public class DateOnly {

    private final int
            mYear,
            mMonth,
            mDay;

    /**
     * Constructs a timezone-nonspecific DateOnly
     *
     * @param dateStr date string of the form <code>yyyy-mm-dd</code>
     */
    public DateOnly(String dateStr) throws ParseException {
        // break the date up into its constituent parts
        String[] dateInfo = dateStr.split("-");

        // validate our split datestring
        if (dateInfo.length != 3) {
            throw new ParseException(
                    "Expected datestring format 'yyyy-mm-dd' but found: " + dateStr, 0
            );
        }

        // array indices for date parsing
        final int year = 0;
        final int month = 1;
        final int day = 2;

        // unpack this array
        mYear = Integer.parseInt(dateInfo[year]);
        mMonth = Integer.parseInt(dateInfo[month]);
        mDay = Integer.parseInt(dateInfo[day]);
    }

    /**
     * Constructs a timezone-nonspecific DateOnly
     *
     * @param year  the year
     * @param month 1-indexed month value (Jan == 1)
     * @param day   day of the month
     */
    public DateOnly(int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    /**
     * Gets the year
     *
     * @return the year
     */
    public int getYear() {
        return mYear;
    }

    /**
     * Gets the month
     *
     * @return the month
     */
    public int getMonth() {
        return mMonth;
    }

    /**
     * Gets the day
     *
     * @return the day
     */
    public int getDay() {
        return mDay;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.ROOT,
                "%04d-%02d-%02d", mYear, mMonth, mDay
        );
    }
}
