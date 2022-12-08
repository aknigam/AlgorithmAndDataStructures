package com.survey;

/**
 * Created by a.nigam on 20/07/17.
 */
public class Log {

    public static void setLevel(int level) {
        Log.level = level;
    }

    static int level = 1;
    public static int INFO = 1;
    public static int DEBUG = 2;
    static int ERROR = 0;




    public static void error(String msg) {
        if(level >=  ERROR)
        System.out.println("ERROR\t"+ msg);
    }

    public static void debug(String msg) {

        if(level >=  DEBUG)
            System.out.println("DEBUG\t"+ msg);
    }

    public static void info(String msg) {
        if(level >=  INFO)
            System.out.println("INFO\t"+ msg);
    }
}
