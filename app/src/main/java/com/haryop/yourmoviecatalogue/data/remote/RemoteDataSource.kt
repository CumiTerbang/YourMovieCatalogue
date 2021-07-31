package com.haryop.yourmoviecatalogue.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) : BaseDataSource() {

    suspend fun getDetail(imdb_id: String) = getResult { apiServices.getDetail(imdb_id) }

}