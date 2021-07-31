package com.haryop.yourmoviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.haryop.yourmoviecatalogue.data.Resource
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel_Item
import com.haryop.yourmoviecatalogue.data.remote.ApiServices
import com.haryop.yourmoviecatalogue.data.remote.RemoteDataSource
import com.haryop.yourmoviecatalogue.data.remote.SearchPagingDataSource
import com.haryop.yourmoviecatalogue.utils.ConstantsObj
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class OMDbRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val apiServices: ApiServices
) {

    fun getDetail(imdb_id: String) = performGetDetailOperation(
        networkCall = { remoteDataSource.getDetail(imdb_id) }
    )

    fun <A> performGetDetailOperation(
        networkCall: suspend () -> Resource<A>
    ): LiveData<Resource<DetailDataModel>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                val listSource = responseStatus.data!! as DetailDataModel
                emit(Resource.success(listSource))

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }


    fun getPagingSearchLiveData(
        query: String,
        pagingConfig: PagingConfig = getDefaultPageConfig()
    ): LiveData<PagingData<SearchDataModel_Item>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                SearchPagingDataSource(apiServices, query)
            }
        ).liveData
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = ConstantsObj.SEARCH_PAGE_SIZE, enablePlaceholders = true)
    }


}