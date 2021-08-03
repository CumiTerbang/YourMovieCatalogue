package com.haryop.yourmoviecatalogue.ui.homepage

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel_Item
import com.haryop.yourmoviecatalogue.data.repository.OMDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: OMDbRepository
) : ViewModel() {

    fun fetchSearchLiveData(query:String): LiveData<PagingData<DetailDataModel>> {
        return repository.getPagingSearchLiveData(query)
            .map { it.map { it } }
            .cachedIn(viewModelScope)
    }

}
