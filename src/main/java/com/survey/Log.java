package com.survey;

/**
 * Created by a.nigam on 20/07/17.
 */
public class Log {

    private static final String ERROR = "ERROR\t";

    public static void error(String msg) {
        System.out.println(ERROR+ msg);
    }
}
