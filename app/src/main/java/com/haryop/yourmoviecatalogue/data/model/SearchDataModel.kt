package com.haryop.yourmoviecatalogue.data.model

import com.google.gson.annotations.SerializedName

data class SearchDataModel(
    @SerializedName("Search") val Search: List<DetailDataModel>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val Response: String
)

data class SearchDataModel_Item(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String,
)
