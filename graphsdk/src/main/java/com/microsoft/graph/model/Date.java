package com.microsoft.graph.model;

import static java.lang.Integer.parseInt;

/**
 * A timezone-nonspecific date
 */
public class Date {

    /**
     * Array indices for date parsing
     */
    private static final int
            sYear = 0,
            sMonth = 1,
            sDay = 2;

    /**
     * The raw string source of this object
     */
    private final String rawDateString;

    private final int
            mYear,
            mMonth,
            mDay;

    /**
     * Constructs a timezone-nonspecific <code>Date</code>
     *
     * @param dateStr date string of the form <code>yyyy-mm-dd</code>
     */
    public Date(String dateStr) {
        // save the String from which this object is derived
        rawDateString = dateStr;

        // break the date up into its constituent parts
        String[] dateInfo = rawDateString.split("-");

        // unpack this array
        mYear = parseInt(dateInfo[sYear]);
        mMonth = parseInt(dateInfo[sMonth]);
        mDay = parseInt(dateInfo[sDay]);
    }

    /**
     * Gets the source {@link String} data from which this object is derived
     *
     * @return source string data
     */
    public String getRawDateString() {
        return rawDateString;
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
        return mYear
                + "-"
                + mMonth
                + "-"
                + mDay;
    }
}
