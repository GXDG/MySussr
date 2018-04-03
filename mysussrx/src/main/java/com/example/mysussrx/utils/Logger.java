package com.example.mysussrx.utils;

import android.util.Log;


/**
 * Created by DELL on 2017年9月21日 021.
 * <p>
 * 很简单但是可以显示行号和方法名的Log工具
 *
 * @author zjx E-mail：n.zjx@163.com
 */
public class Logger {

    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    private static String sFileName;
    private static String sMethodName;
    private static int sLineNumber;

    //  private static boolean isDebug = BuildConfig.DEBUG;
    private static boolean isDebug = true;

    private Logger() {

    }

    public static void setIsDebug(boolean isDebug) {
        Logger.isDebug = isDebug;
    }

    /**
     * 0：dalvik.system.VMStack.getThreadStackTrace(Native Method)
     * 1：java.lang.Thread.getStackTrace(Thread.java)
     * 2：Logger.getMethodNames()
     * 3：Logger.i(Logger.java)
     * 4：调用i方法的类
     */
    private static String getMethodNames(int offset) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        sFileName = elements[offset].getFileName();
        sMethodName = elements[offset].getMethodName();
        sLineNumber = elements[offset].getLineNumber();
        return "(" + sFileName + ":" + sLineNumber + ")" + "." + sMethodName;
    }

    public static void v(Object msg) {
        printLog(VERBOSE, msg);
    }

    public static void d(Object msg) {
        printLog(DEBUG, msg);
    }

    public static void i(Object msg) {
        printLog(INFO, msg);
    }

    public static void w(Object msg) {
        printLog(WARN, msg);
    }

    public static void e(Object msg) {
        printLog(ERROR, msg);
    }

    private static void printLog(int level, Object obj) {
        if (!isDebug) {
            return;
        }
        String msg = obj == null ? "null" : obj.toString();

        switch (level) {
            case VERBOSE:
                Log.v(getMethodNames(5), msg);
                break;
            case DEBUG:
                Log.d(getMethodNames(5), msg);
                break;
            case INFO:
                Log.i(getMethodNames(5), msg);
                break;
            case WARN:
                Log.w(getMethodNames(5), msg);
                break;
            case ERROR:
                Log.e(getMethodNames(5), msg);
                break;
            case ASSERT:
                Log.e(getMethodNames(5), msg);
                break;
            default:
                Log.w("LogTips", "没有预设的输出等级" + msg);
                break;
        }
    }

    //打印超长日志
    private static int LOG_MAXLENGTH = 2000;

    public static void Le(String TAG, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = LOG_MAXLENGTH;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                Log.e(TAG + i, msg.substring(start, end));
                start = end;
                end = end + LOG_MAXLENGTH;
            } else {
                Log.e(TAG, msg.substring(start, strLength));
                break;
            }
        }
    }

}
