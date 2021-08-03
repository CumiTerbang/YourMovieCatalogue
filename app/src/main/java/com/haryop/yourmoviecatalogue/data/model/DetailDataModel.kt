package com.haryop.yourmoviecatalogue.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
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
    @SerializedName("Ratings") var ratings: List<DetailDataModel_Rating> = ArrayList(),
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
    @SerializedName("Value") var value: String=""
)

var gson = Gson()
class RatingsConverters {
    @TypeConverter
    fun listToJson(value: List<DetailDataModel_Rating>?) = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = gson.fromJson(value, Array<DetailDataModel_Rating>::class.java).toList()
}