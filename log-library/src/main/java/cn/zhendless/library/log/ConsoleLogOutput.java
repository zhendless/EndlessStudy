package cn.zhendless.library.log;

import android.util.Log;

public class ConsoleLogOutput extends cn.zhendless.library.log.LogOutput {

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
