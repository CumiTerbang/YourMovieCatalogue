package com.haryop.yourmoviecatalogue.utils

object ConstantsObj {
    const val OMDB_APIKEY = "${APIKEY_Obj.YOUR_OMDB_APIKEY}"
    const val OMDB_BASEURL = "https://www.omdbapi.com/"
    const val OMDB_BASEPARAM = "apiKey=${OMDB_APIKEY}"
    const val SEARCH_PAGE_SIZE = 10
    const val SEARCH_PAGE_DEFAULT_INDEX = 1

    const val HOME_PAGE = 1
    const val DETAIL_PAGE = 2
}