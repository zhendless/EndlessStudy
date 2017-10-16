/*
 * Name        : LogTracer.java
 * Classes     : LogTracer
 * Version     : 1.0
 * Date        : 13-9-16
 *
 * Copyright 2013 CMCC.  All rights reserved.
 * This material, including documentation and any related computer
 * programs, is protected by copyright controlled by CMCC.  All
 * rights are reserved.  Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the
 * prior written consent of CMCC.  This material also contains
 * confidential information which may not be disclosed to others
 * without the prior written consent of CMCC.
 */
package cn.papayamobile.log;

import android.content.Context;
import android.os.Environment;

import com.cmcc.datiba.utils.DTBConstants;

import java.io.File;

public class LogTracer {
    public static final int RELEASE_LOG_LEVEL = 3;
    public static final int CRITICAL_LOG_LEVEL = 2;
    public static final int DEBUG_LOG_LEVEL = 1;

    private static LogUtil mLogger;

    /**
     * config parameters for log printer this will be called at the init step of
     * your program
     */
    public static void init(Context context, int logLevel) {
        mLogger = new LogUtil();
        mLogger.setEnabled(true);
        mLogger.setLogLevel(logLevel);
        File folder;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            folder = Environment.getExternalStorageDirectory();
        } else {
            folder = context.getFilesDir();
        }

        mLogger.setFileFolder(DTBConstants.LOG_FILE_FOLDER_NAME);
        mLogger.addLogOutput(LogOutputType.FILE_LOG_OUTPUT);
        mLogger.addLogOutput(LogOutputType.CONSOLE_LOG_OUTPUT);
    }

    /**
     * Print the debug log
     */
    public static void printLogLevelDebug(String tag, String msg) {
        mLogger.writeLog(tag, msg, DEBUG_LOG_LEVEL);
    }

    /**
     * Print the critical log
     */
    public static void printLogLevelCritical(String tag, String msg) {
        mLogger.writeLog(tag, msg, CRITICAL_LOG_LEVEL);
    }

    /**
     * Print the release log
     */
    public static void printLogLevelRelease(String tag, String msg) {
        mLogger.writeLog(tag, msg, RELEASE_LOG_LEVEL);
    }

    public static void printException(Throwable e) {
        mLogger.writeExceptionStack(e, RELEASE_LOG_LEVEL);
    }
}
