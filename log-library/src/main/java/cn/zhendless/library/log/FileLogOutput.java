package cn.zhendless.library.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class FileLogOutput extends LogOutput {

    private static final String LOG_FILE = "log.txt";
    private static final String LOG_BACKUP_FILE = "log_old.txt";
    private String mFileFolder;
    private OutputStream mLogStream;
    private long mFileSize;
    private static final int LOG_MAXSIZE = 1 * 1024 * 1024;
    private Object lockObj = new Object();
    private Calendar mDate = Calendar.getInstance();
    private StringBuffer mBuffer = new StringBuffer();

    public FileLogOutput() {
        super(LogOutputType.FILE_LOG_OUTPUT);
    }

    public void setFileFolder(String fileFolder) {
        mFileFolder = fileFolder;
    }

    /**
     * create log string, add the information of time.
     *
     * @param tag , tag of log
     * @param msg , content of log
     * @return log with time.
     */
    private String getLogStr(String tag, String msg) {

        mDate.setTimeInMillis(System.currentTimeMillis());

        mBuffer.setLength(0);
        mBuffer.append("[");
        mBuffer.append(tag);
        mBuffer.append(" : ");
        mBuffer.append(mDate.get(Calendar.MONTH) + 1);
        mBuffer.append("-");
        mBuffer.append(mDate.get(Calendar.DATE));
        mBuffer.append(" ");
        mBuffer.append(mDate.get(Calendar.HOUR_OF_DAY));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.MINUTE));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.SECOND));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.MILLISECOND));
        mBuffer.append("] ");
        mBuffer.append(msg);

        return mBuffer.toString();
    }

    /**
     * rename log file
     *
     * @return true for success, false for fail.
     */
    private boolean renameLogFile() {

        synchronized (lockObj) {

            File file = new File(mFileFolder, LOG_FILE);
            File destFile = new File(mFileFolder, LOG_BACKUP_FILE);
            if (destFile.exists()) {
                destFile.delete();
            }

            if (file.renameTo(destFile)) {
                if (file.exists()) {
                    return file.delete();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * write log into log file.
     *
     * @param tag
     * @param content
     */
    @Override
    public void writeLog(String tag, String content) {
        synchronized (lockObj) {
            if (mLogStream != null) {
                try {
                    byte[] d = getLogStr(tag, content).getBytes("utf-8");
                    if (mFileSize > LOG_MAXSIZE) {
                        close();
                        renameLogFile();
                        if (!open()) {
                            return;
                        }
                    }
                    mLogStream.write(d);
                    mLogStream.write("\r\n".getBytes());
                    mLogStream.flush();
                    mFileSize += d.length;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get input stream of temp log.
     *
     * @return
     */
    @Override
    public boolean open() {
        if (mFileFolder == null) {
            return false;
        }

        // mlogStream already exists, so open success
        if (mLogStream != null) {
            return true;
        }

        // mlogStream isn't already exists
        try {
            File folder = new File(mFileFolder);
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    return false;
                }
            }

            File file = new File(mFileFolder, LOG_FILE);

            mLogStream = new FileOutputStream(file, true);
            mFileSize = file.length();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * close output stream of log
     *
     * @return
     */
    @Override
    public void close() {
        try {
            if (mLogStream != null) {
                mLogStream.close();
                mLogStream = null;
                mFileSize = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isOpened() {
        return (mLogStream != null);
    }
}
