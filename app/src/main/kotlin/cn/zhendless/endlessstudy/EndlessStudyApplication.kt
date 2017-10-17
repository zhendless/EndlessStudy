package cn.zhendless.endlessstudy

import android.app.Application
import android.util.Log

class EndlessStudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "EndlessStudyApplication -> onCreate()")
        initTools()
    }

    private fun initTools() {
        CrashHandler.getInstance().init()
    }

    companion object {
        private val TAG = EndlessStudyApplication::class.java.simpleName
    }

}