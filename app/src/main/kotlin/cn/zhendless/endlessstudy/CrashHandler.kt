package cn.zhendless.endlessstudy

import android.util.Log

class CrashHandler private constructor() : Thread.UncaughtExceptionHandler {

    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    fun init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread?, throwable: Throwable?) {
        Log.e(TAG, "CrashHandler -> uncaughtException, throwable = " + throwable.toString())
    }

    companion object {
        private val TAG = CrashHandler::class.java.simpleName
        private val mInstance = CrashHandler()
        fun getInstance(): CrashHandler {
            return mInstance
        }
    }

}