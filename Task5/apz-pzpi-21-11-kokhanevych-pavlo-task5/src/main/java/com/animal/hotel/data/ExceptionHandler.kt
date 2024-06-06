package com.animal.hotel.data

import com.animal.hotel.data.utils.AppException
import retrofit2.HttpException
import java.io.IOException

open class ExceptionHandler {

    /**
     * Map network and parse exceptions into in-app exceptions.
     * @throws BackendException
     * @throws ParseBackendResponseException
     * @throws ConnectionException
     */
    suspend fun <T> wrapRetrofitExceptions(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: AppException) {
            throw e
        } catch (e: HttpException) {
            throw createBackendException(e)
        } catch (e: IOException) {
            throw AppException.ConnectionException(e)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun createBackendException(e: HttpException): AppException.BackendException {
        return AppException.BackendException("HTTP ${e.code()}: ${e.message()}", e)
    }
}