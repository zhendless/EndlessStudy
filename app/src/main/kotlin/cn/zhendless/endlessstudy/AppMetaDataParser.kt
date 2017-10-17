package cn.papayamobile.papayacast

import android.content.Context
import android.content.pm.PackageManager

object AppMetaDataParser {

    private val META_DATA_NAME_LOG_LEVEL = "LOG_LEVEL"

    private fun getMetaValue(context: Context, key: String): String {
        val packageManager = context.packageManager
        val appInfo = packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
        return appInfo.metaData[key].toString()
    }

}