/*
 * Name        : LogUtil.java
 * Classes     : LogUtil
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

import java.util.LinkedList;
import java.util.List;

public class LogUtil {

    private List<LogOutput> mOutputList;
    private boolean mIsEnabled;
    private int mLogLevel;
    private String mFileFolder;

    public LogUtil() {
        mOutputList = new LinkedList<LogOutput>();
        mIsEnabled = true;
        mLogLevel = -1;
    }

    /**
     * set whether the log function is enabled
     */
    public void setEnabled(boolean isEnabled) {
        mIsEnabled = isEnabled;
    }

    /**
     * set baseline level for the log just print the log above mLogLevel
     */
    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    /**
     * set the file path for the log
     */
    public void setFileFolder(String fileFolder) {
        mFileFolder = fileFolder;
    }

    /**
     * print the log in the supported log type
     */
    public void writeLog(String tag, String msg, int level) {
        if (!mIsEnabled) {
            return;
        }

        if (tag == null) {
            tag = "TAG_NULL";
        }

        if (msg == null) {
            msg = "MSG_NULL";
        }

        if (level >= mLogLevel) {
            for (LogOutput output : mOutputList) {
                if (output.isOpened() || output.open()) {
                    output.writeLog(tag, msg);
                }
            }
        }
    }

    public void writeExceptionStack(Throwable e, int level) {
        StringBuilder msg = new StringBuilder(e.toString() + "\r\n");
        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement element : elements) {
            msg.append(element.getClassName()).append(".").append(element.getMethodName()).append(" line: ").append(element.getLineNumber()).append("\r\n");
        }
        writeLog("Exception Stack", msg.toString(), level);
    }

    /**
     * add the supported log type
     */
    public void addLogOutput(int outputType) {
        if (findOutputByType(outputType) == null) {
            switch (outputType) {
                case LogOutputType.CONSOLE_LOG_OUTPUT:
                    ConsoleLogOutput consoleOutput = new ConsoleLogOutput();
                    addLogOutput(consoleOutput);
                    break;
                case LogOutputType.FILE_LOG_OUTPUT:
                    FileLogOutput fileOutput = new FileLogOutput();
                    fileOutput.setFileFolder(mFileFolder);
                    addLogOutput(fileOutput);
                    break;
                default:
                    break;
            }
        }
    }

    public void removeLogOutput(int outputType) {
        LogOutput output = findOutputByType(outputType);
        removeLogOutput(output);
    }

    public void addLogOutput(LogOutput output) {
        if (output != null && findOutputByType(output.outputType()) == null) {
            mOutputList.add(output);
        }
    }

    public void removeLogOutput(LogOutput output) {
        if (output != null) {
            mOutputList.remove(output);
        }
    }

    private LogOutput findOutputByType(int outputType) {
        for (LogOutput output : mOutputList) {
            if (output.outputType() == outputType) {
                return output;
            }
        }
        return null;
    }
}
