package com.app.heemcream

import android.content.Context
import android.content.SharedPreferences



class SaveSharedPreference {
    companion object {
        private const val PREF_USER = "username"
        private const val PREF_LOC = "location"

        private fun getSharedPreferences(ctx: Context): SharedPreferences {
            return ctx.getSharedPreferences(PREF_USER, Context.MODE_PRIVATE)
        }

        fun setUserName(ctx: Context, userName: String) {
            val editor = getSharedPreferences(ctx).edit()
            editor.putString(PREF_USER, userName)
            editor.apply()
        }

        fun getUserName(ctx: Context): String {
            return getSharedPreferences(ctx).getString(PREF_USER, "") ?: ""
        }

        fun setLocation(ctx: Context, location: String) {
            val editor = getSharedPreferences(ctx).edit()
            editor.putString(PREF_LOC, location)
            editor.apply()
        }

        fun getLocation(ctx: Context): String {
            return getSharedPreferences(ctx).getString(PREF_LOC, "") ?: ""
        }

        fun clearUserName(ctx: Context) {
            val editor = getSharedPreferences(ctx).edit()
            editor.remove(PREF_USER)
            editor.apply()
        }

    }
}