

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch

/* add an enum to represent all the available statuses */
enum class MarsApiStatus{ LOADING, ERROR, DONE}

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {


    private val _status = MutableLiveData<MarsApiStatus>()      //  updating _response to _status
    val status: LiveData<MarsApiStatus>
    get() = _status


    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
    get() = _properties


    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
    get() = _navigateToSelectedProperty


    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)     // by Def , it shows all
    }

    /**
     * calling the Retrofit service
     * using coroutines with exception handling, instead of using callbacks
     *
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            try {

                        // Calling getProperties() from the MarsApi service creates and starts the network call on a background thread..
                _properties.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE

            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    fun updateFilter(filter: MarsApiFilter) {       // this f() called from OverViewFrg , when Manually seleted from  the top Menu
        getMarsRealEstateProperties(filter)
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}

