package com.haryop.yourmoviecatalogue.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies")
data class DetailDataModel(
    @SerializedName("Title") var title: String="",
    @SerializedName("Year") var year: String="",
    @SerializedName("Rated") var rated: String="",
    @SerializedName("Released") var released: String="",
    @SerializedName("Runtime") var runtime: String="",
    @SerializedName("Genre") var genre: String="",
    @SerializedName("Director") var director: String="",
    @SerializedName("Writer") var writer: String="",
    @SerializedName("Actors") var actors: String="",
    @SerializedName("Plot") var plot: String="",
    @SerializedName("Language") var language: String="",
    @SerializedName("Country") var country: String="",
    @SerializedName("Awards") var awards: String="",
    @SerializedName("Poster") var poster: String="",

    @Ignore
    @SerializedName("Ratings") var ratings: List<DetailDataModel_Rating> = emptyList(),

    @SerializedName("Metascore") var metascore: String="",
    @SerializedName("imdbRating") var imdbRating: String="",
    @SerializedName("imdbVotes") var imdbVotes: String="",

    @PrimaryKey
    @SerializedName("imdbID")
    var imdbID: String="",

    @SerializedName("Type") var type: String="",
    @SerializedName("DVD") var dvd: String="",
    @SerializedName("BoxOffice") var boxOffice: String="",
    @SerializedName("Production") var production: String="",
    @SerializedName("Website") var website: String="",
    @SerializedName("Response") var response: String=""
)

data class DetailDataModel_Rating(
    @SerializedName("Source") var source: String="",
    @SerializedName("value") var value: String=""
)
