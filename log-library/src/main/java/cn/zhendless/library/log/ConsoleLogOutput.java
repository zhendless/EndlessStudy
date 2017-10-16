/*
 * Name        : ConsoleLogOutput.java
 * Classes     : ConsoleLogOutput
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

import android.util.Log;

public class ConsoleLogOutput extends LogOutput {

    public ConsoleLogOutput() {
        super(LogOutputType.CONSOLE_LOG_OUTPUT);
    }

    @Override
    public void writeLog(String tag, String content) {
        Log.d(tag, content);
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpened() {
        return true;
    }

}
