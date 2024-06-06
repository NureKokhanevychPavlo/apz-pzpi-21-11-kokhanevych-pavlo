package com.animal.hotel.data.utils

sealed class AppException(message: String, cause: Throwable? = null) : Exception(message, cause) {
    class BackendException(message: String, cause: Throwable? = null) : AppException(message, cause)
    class ParseBackendResponseException(cause: Throwable) : AppException("Error parsing backend response", cause)
    class ConnectionException(cause: Throwable) : AppException("Connection error", cause)
}