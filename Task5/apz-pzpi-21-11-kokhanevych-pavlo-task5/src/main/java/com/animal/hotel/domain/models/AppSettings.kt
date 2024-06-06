package com.animal.hotel.domain.models


interface AppSettings {
    /**
     * Get auth token of the current logged-in user.
     */
    fun getCurrentToken(): String?

    /**
     * Set auth token of the logged-in user.
     */
    fun setCurrentToken(token: String?)

    fun setUserId(userId: Int?)

    fun getUserId(): Int?

    fun setEmail(string: String?)

    fun getEmail(): String?
}