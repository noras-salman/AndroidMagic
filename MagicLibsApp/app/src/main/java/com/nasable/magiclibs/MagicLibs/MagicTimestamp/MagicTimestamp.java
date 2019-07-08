package com.nasable.magiclibs.MagicLibs.MagicTimestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MagicTimestamp {


    private long timestamp;

    /**
     * Get a new timestamp (currentTimeMillis)
     **/
    public MagicTimestamp() {
        timestamp = System.currentTimeMillis();
    }

    /**
     * pass a long timestamp
     **/
    public MagicTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * returns a long timestamp value in (milliseconds)
     *
     * @param timestamp the timestamp as a string
     * @param pattern   the pattern that the passed string is written in ex: yyyy-MM-dd HH:mm:ss
     **/
    public MagicTimestamp(String timestamp, String pattern) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(pattern);
        Date d = formatter.parse(timestamp);
        this.timestamp = d.getTime();
    }

    /**
     *
     * @param timestamp
     * @throws ParseException
     */
    public MagicTimestamp(String timestamp) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = formatter.parse(timestamp);
        this.timestamp = d.getTime();
    }

    /**
     *
     * @return
     */
    public long getTimestamp() {
        return this.timestamp;
    }

    /**
     *
     * @return
     * @throws ParseException
     */
    public String getTimestampLocalFromUTC() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", simpleDateFormat.parse( getSQLTimestamp())).toString();
    }



    /**
     * Number date functions
     **/

    public int getDateYear() {
        return Integer.valueOf(getYearYYYY());
    }

    public int getDateMonth() {
        return Integer.valueOf(getMonthMM());
    }

    public int getDateDay() {
        return Integer.valueOf(getDayDD());
    }

    public int getDateDayOfYear() {
        return getDayOfYear(getDateYear(), getDateMonth(), getDateDay());
    }

    public int getDateWeekNumber() {
        return getDateDayOfYear() / 7;
    }

    /**
     * ======== Diff Functions ========
     **/

    /**
     *
     * @param magicTimestamp
     * @return
     */
    public long getDiffMillis(MagicTimestamp magicTimestamp) {
        return Math.abs(magicTimestamp.getTimestamp() - this.timestamp);
    }

    /**
     *
     * @param magicTimestamp
     * @return
     */
    public long getDiffSeconds(MagicTimestamp magicTimestamp) {
        return getDiffMillis(magicTimestamp) / 1000;
    }

    /**
     *
     * @param magicTimestamp
     * @return
     */
    public long getDiffMinuets(MagicTimestamp magicTimestamp) {
        return getDiffSeconds(magicTimestamp) / 60;
    }

    /**
     *
     * @param magicTimestamp
     * @return
     */
    public long getDiffHours(MagicTimestamp magicTimestamp) {
        return getDiffMinuets(magicTimestamp) / 60;
    }

    /**
     *
     * @param magicTimestamp
     * @return
     */
    public int getDiffDays(MagicTimestamp magicTimestamp) {
        int y1 = getDateYear();
        int y2 = magicTimestamp.getDateYear();

        int doy1 = getDateDayOfYear();
        int doy2 = magicTimestamp.getDateDayOfYear();

        if (y1 == y2)
            return Math.abs(doy1 - doy2);

        int diff = 0;
        if (y1 > y2) {
            for (int i = y2; i < y1; i++)
                diff += getDayOfYear(i, 12, 31);
            diff += doy1;
        } else {
            for (int i = y1; i < y2; i++)
                diff += getDayOfYear(i, 12, 31);
            diff += doy2;
        }
        return diff;
    }

    /**
     * ======== String Functions ========
     **/

    public String getYearYYYY() {

        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public String getMonthMM() {

        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(date);
    }

    public String getDayDD() {

        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(date);
    }

    public String getHourMinute() {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    public String getDayMonth() {

        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd MMM");
        return formatter.format(date);
    }

    public String getDayMonthYear() {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("dd MMM yy");
        return formatter.format(date);
    }

    /**
     *
     * **/
    public String getSQLTimestamp() {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


    private MagicTimestamp getMostRecent(MagicTimestamp magicTimestamp) {
        if (magicTimestamp.getTimestamp() > this.timestamp)
            return magicTimestamp;

        return this;
    }

    private MagicTimestamp getOldest(MagicTimestamp magicTimestamp) {
        if (magicTimestamp.getTimestamp() < this.timestamp)
            return magicTimestamp;

        return this;
    }


    /**
     * returns a human readable difference
     *
     * @param magicTimestamp usually used as now (System.currentTimeMillis())or.
     **/
    public String getDiffHumanReadable(MagicTimestamp magicTimestamp) {

        int days = getDiffDays(magicTimestamp);

      //  MagicTimestamp mostRecent = getMostRecent(magicTimestamp);
        MagicTimestamp oldest = getOldest(magicTimestamp);


        if (days == 0)
            return oldest.getHourMinute();

        else if (getDateYear() != magicTimestamp.getDateYear())
            return oldest.getDayMonthYear();
        else
            return oldest.getDayMonth();
    }

    /**
     * ======== HELPERS Functions ========
     **/


    /**
     * if (year is not divisible by 4) then (it is a common year)
     * else if (year is not divisible by 100) then (it is a leap year)
     * else if (year is not divisible by 400) then (it is a common year)
     * else (it is a leap year)
     **/
    private boolean isLeapYear(int year) {
        if (year % 4 != 0)
            return false;
        else if (year % 100 != 0)
            return true;
        else if (year % 400 != 0)
            return false;
        return true;
    }

    private int[] getDaysOfMonth(int year) {
        int feb = 28;
        if (isLeapYear(year))
            feb = 29;
        return new int[]{31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }

    private int getNumberOfDays(int year, int month) {
        return getDaysOfMonth(year)[month - 1];
    }

    //start at 1
    private int getDayOfYear(int year, int month, int day) {
        int count = 0;
        for (int i = 1; i < month; i++)
            count += getNumberOfDays(year, i);
        count += day;
        return count;
    }


}

