package cn.zhendless.library.log;

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
