package com.animal.hotel.data

import android.content.Context
import com.animal.hotel.domain.models.AppSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesAppSettings @Inject constructor(
    @ApplicationContext appContext: Context
) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun setCurrentToken(token: String?) {
        val editor = sharedPreferences.edit()
        if (token == null)
            editor.remove(PREF_CURRENT_ACCOUNT_TOKEN)
        else
            editor.putString(PREF_CURRENT_ACCOUNT_TOKEN, token)
        editor.apply()
    }

    override fun setUserId(userId: Int?) {
        val editor = sharedPreferences.edit()
        if (userId == null) {
            editor.remove(USER_ID)
        } else {
            editor.putString(USER_ID, userId.toString())
        }
        editor.apply()
    }

    override fun getUserId(): Int? =
        sharedPreferences.getString(USER_ID, null)?.toInt()

    override fun setEmail(string: String?) {
        val editor = sharedPreferences.edit()
        if (string == null) {
            editor.remove(EMAIL)
        } else {
            editor.putString(EMAIL, string)
        }
        editor.apply()
    }

    override fun getEmail(): String? = sharedPreferences.getString(EMAIL, null)

    override fun getCurrentToken(): String? =
        sharedPreferences.getString(PREF_CURRENT_ACCOUNT_TOKEN, null)

    companion object {
        private const val PREF_CURRENT_ACCOUNT_TOKEN = "currentToken"
        private const val USER_ID = "USER_ID"
        private const val EMAIL = "email"
    }
}