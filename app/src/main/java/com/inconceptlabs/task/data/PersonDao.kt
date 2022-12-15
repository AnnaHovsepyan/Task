package com.inconceptlabs.task.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(personsData: List<Person>)

    @Query("SELECT * FROM person ORDER BY CASE WHEN :isAsc = 1 THEN age END ASC, CASE WHEN :isAsc = 0 THEN age END DESC")
    fun getPersonData(isAsc: Boolean): Flow<Array<Person>>

    @Query("SELECT * FROM person")
    fun getPersonData(): Flow<Array<Person>>
}
