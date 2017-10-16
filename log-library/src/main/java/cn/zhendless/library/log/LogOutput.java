/*
 * Name        : LogOutput.java
 * Classes     : LogOutput
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

public abstract class LogOutput {

    private int mType;

    public LogOutput(int type) {
        mType = type;
    }

    public int outputType() {
        return mType;
    }

    public abstract void writeLog(String tag, String content);

    public abstract boolean open();

    public abstract void close();

    public abstract boolean isOpened();
}
