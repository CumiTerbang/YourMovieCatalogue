package com.haryop.yourmoviecatalogue.data.model

import com.google.gson.annotations.SerializedName

data class SearchDataModel(
    @SerializedName("Search") val Search: List<DetailDataModel>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val Response: String
)