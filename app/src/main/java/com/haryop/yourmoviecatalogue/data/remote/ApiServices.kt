package com.haryop.yourmoviecatalogue.data.remote

import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel
import com.haryop.yourmoviecatalogue.utils.ConstantsObj
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("?" + ConstantsObj.OMDB_BASEPARAM)
    suspend fun getPagingSearch(
        @Query("s") search_query: String?,
        @Query("page") page: String?
    ): SearchDataModel

    @GET("?" + ConstantsObj.OMDB_BASEPARAM)
    suspend fun getDetail(@Query("i") imdb_id: String?): Response<DetailDataModel>

}