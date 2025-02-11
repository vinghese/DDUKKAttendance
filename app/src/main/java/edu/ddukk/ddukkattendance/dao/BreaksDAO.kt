package edu.ddukk.ddukkattendance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.ddukk.ddukkattendance.models.Breaks

@Dao
interface BreaksDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBreaks(breaks: Breaks): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBreaks(breaks: Breaks)

    @Delete()
    suspend fun deleteBreaks(breaks: Breaks)

    @Query("Select * from breaks")
    suspend fun getAllBreaks(): List<Breaks>
}