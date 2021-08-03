package com.haryop.yourmoviecatalogue.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel_Item
import com.haryop.yourmoviecatalogue.utils.ConstantsObj
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class SearchPagingDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val search_query: String
) : PagingSource<Int, DetailDataModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DetailDataModel> {
        val page = params.key ?: ConstantsObj.SEARCH_PAGE_DEFAULT_INDEX
        return try {
            val response = apiServices.getPagingSearch(search_query, page.toString())
            if(response.Response.equals("False")){
                var e = Exception()
                return LoadResult.Error(e)
            }
            LoadResult.Page(
                response.Search,
                prevKey = if (page == ConstantsObj.SEARCH_PAGE_DEFAULT_INDEX) null else page - 1,
                nextKey = if (response.Search.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            Log.e("NewsListPagingSource", "error IOException")
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e("NewsListPagingSource", "error HttpException")
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DetailDataModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}