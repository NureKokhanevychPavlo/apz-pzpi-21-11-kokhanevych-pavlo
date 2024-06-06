package com.animal.hotel.presentation.ui.base


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animal.hotel.R
import com.animal.hotel.data.utils.AppException
import com.animal.hotel.presentation.utils.MutableLiveEvent
import com.animal.hotel.presentation.utils.publishEvent
import com.animal.hotel.presentation.utils.share
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel(): ViewModel() {

    open var isTokenPresent = MutableLiveData<Boolean>(false)
    private val _showErrorMessageEvent = MutableLiveEvent<Int>()
    val showErrorMessageEvent = _showErrorMessageEvent.share()

    fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: AppException.ConnectionException) {
                isTokenPresent.value = false
                e.printStackTrace()
                _showErrorMessageEvent.publishEvent(R.string.error_connect)
            } catch (e: AppException.BackendException) {
                isTokenPresent.value = false
                e.printStackTrace()
                _showErrorMessageEvent.publishEvent(R.string.error_server)
            } catch (e: Exception) {
                isTokenPresent.value = false
                _showErrorMessageEvent.publishEvent(R.string.error_unknown)
            }
        }
    }
}