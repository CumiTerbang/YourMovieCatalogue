package com.haryop.yourmoviecatalogue.ui.detailpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.haryop.yourmoviecatalogue.data.Resource
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.repository.OMDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: OMDbRepository
) : ViewModel() {

    private val _id = MutableLiveData<String>()

    private val _detaildata = _id.switchMap { id ->
        repository.getDetail(id)
    }

    val getDetailData: LiveData<Resource<DetailDataModel>> = _detaildata

    fun start(imdb_id: String) {
        _id.value = imdb_id
    }

}