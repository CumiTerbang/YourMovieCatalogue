package com.haryop.yourmoviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<DetailDataModel>>

    @Query("SELECT * FROM movies WHERE imdbID = :imdb_id")
    fun getMovie(imdb_id: String): LiveData<DetailDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<DetailDataModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: DetailDataModel)

}