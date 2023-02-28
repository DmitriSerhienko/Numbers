package com.dimas.numbers.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dimas.numbers.R
import com.dimas.numbers.data.db.FactItem
import com.dimas.numbers.data.db.FactsEntity
import com.dimas.numbers.data.network.NetworkResult
import com.dimas.numbers.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FactsViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val readFacts: LiveData<List<FactsEntity>> = repository.local.getAllFacts
    val myResponse: MutableLiveData<NetworkResult<FactItem>> = MutableLiveData()

    private fun insertFact(inputNumber: FactsEntity) = viewModelScope.launch {
        repository.local.insertNumber(inputNumber)
    }

    fun getRandomFact() = viewModelScope.launch {
        getRandomFactSafeCall()
    }

    fun getInputFact(input: Long) = viewModelScope.launch {
        getInputFactSafeCall(input)

    }

    private suspend fun getRandomFactSafeCall() {
        myResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            val response = repository.remote.getRandomFact()
            myResponse.value = handleNumberResponse(response)

            val fact = myResponse.value!!.data
            if (fact != null) {
                offlineCache(fact)
            }
        } else {
            myResponse.value = NetworkResult.Error(R.string.no_internet.toString())
        }
    }

    private suspend fun getInputFactSafeCall(inputFact: Long) {
        myResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            val response = repository.remote.getInputFact(inputFact)
            myResponse.value = handleNumberResponse(response)

            val fact = myResponse.value!!.data
            if (fact != null) {
                offlineCache(fact)
            }
        } else {
            myResponse.value = NetworkResult.Error(R.string.no_internet.toString())
        }
    }

    private fun handleNumberResponse(response: Response<FactItem>): NetworkResult<FactItem> {
        return when {
            response.toString().contains("timeout") -> {
                NetworkResult.Error(R.string.timeout.toString())
            }
            response.body()!!.aboutNumber.isEmpty() -> {
                NetworkResult.Error(R.string.input_number.toString())
            }
            response.isSuccessful -> {
                val fact = response.body()
                NetworkResult.Success(fact)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun offlineCache(fact: FactItem) {
        val nFact = FactsEntity(fact)
        insertFact(nFact)
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}