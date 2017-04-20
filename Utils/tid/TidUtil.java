package com.ccx.credit.util.tid;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class which provides methods for Tid.
 * 
 * @author Emre
 * 
 */
public final class TidUtil
{
    private static Logger log = LoggerFactory.getLogger(TidUtil.class);
    
    private static String format = "%04d";

    private TidUtil()
    {
    }

    @Deprecated
    public static String genTidOld()
    {
        int rnd = calRandomScore(9999, 1000);
        return Constants.JVM_PID + System.currentTimeMillis() + rnd;
    }

    public static String genTid()
    {
        int rnd = calRandomScore(9999, 1000);
        String hts = null;
        
            int hti = 90;
            hts = String.format(format, hti);
        
        if (hts == null)
        {
            hts = Constants.TID_UNKNOWN_HT;
        }
        
      //  String serverCode = ServiceConstants.SERVER_CODE;
        String serverCode = "1002";
        if (serverCode == null)
        {
            // use "1099" as reserved server code
            serverCode = Constants.TID_DEFAULT_SERVER_CODE;
        }

        return serverCode + hts + System.currentTimeMillis() + rnd;
    }

    public static int calRandomScore(int max, int min)
    {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }
    
    public static String getRandomString( int length ){
        String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for ( int i = 0; i < length; i++ ) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    } 
    
    public static String getUnixTimeStamp ( long timestamp ) {
        String times = Long.toHexString(timestamp);
        System.out.println(times);
        return times;
    }

    public static void main(String[] args)
    {
//        System.out.println(TidUtil.genTidOld());
//        System.out.println(TidUtil.calRandomScore(9999, 1000));
//        System.out.println(TidUtil.genTid(HistoryType.HT_DEBT));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
//        System.out.println(getRandomString(8));
        
//        long times = new Date().getTime();
//        System.out.println(times);
//        getUnixTimeStamp(times);
//        
//        System.out.println(Long.toString(new Date().getTime(), Character.MAX_RADIX));
    	System.out.println(genTid());
    }

}
