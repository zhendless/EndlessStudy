package cn.papayamobile.papayacast

import android.content.Context

object AppSettingManager {

    private val USER_PREFERENCES_FILE = "config"

    /**
     * Save boolean value
     *
     * @param value The setting value
     * @param key The setting key name
     * */
    private fun saveBoolean(context: Context, value: Boolean, key: String) {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    /**
     * Read boolean value
     *
     * @param key      The setting key name
     * @param defValue The default value which be returned when read failed
     * @return The setting value
     */
    private fun readBoolean(context: Context, key: String, defValue: Boolean): Boolean {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sp.getBoolean(key, defValue)
    }

    /**
     * Save string value
     *
     * @param value The setting value
     * @param key   The setting key name
     */
    private fun saveString(context: Context, value: String?, key: String) {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val edit = sp.edit()
        if (value == null) {
            edit.remove(key)
        } else {
            edit.putString(key, value)
        }
        edit.apply()
    }

    /**
     * Read string value
     *
     * @param key      The setting key name
     * @param defValue The default value which be returned when read failed
     * @return The setting value
     */
    private fun readString(context: Context, key: String, defValue: String): String {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sp.getString(key, defValue)
    }

    /**
     * Save float value
     *
     * @param value The setting value
     * @param key   The setting key name
     */
    private fun saveFloat(context: Context, value: Float, key: String) {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putFloat(key, value)
        edit.apply()
    }

    /**
     * Read float value
     *
     * @param key      The setting key name
     * @param defValue The default value which be returned when read failed
     * @return The setting value
     */
    private fun readFloat(context: Context, key: String, defValue: Float): Float {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sp.getFloat(key, defValue)
    }

    /**
     * Save int value
     *
     * @param value The setting value
     * @param key   The setting key name
     */
    private fun saveInt(context: Context, value: Int, key: String) {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    /**
     * Read int value
     *
     * @param key      The setting key name
     * @param defValue The default value which be returned when read failed
     * @return The setting value
     */
    private fun readInt(context: Context, key: String, defValue: Int): Int {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        return sp.getInt(key, defValue)
    }

    /**
     * remove setting item
     *
     * @param key The key name of item which need be removed
     */
    private fun removeValue(context: Context, key: String) {
        val sp = context.getSharedPreferences(USER_PREFERENCES_FILE, Context.MODE_PRIVATE)
        val edit = sp.edit()
        edit.remove(key)
        edit.apply()
    }
}