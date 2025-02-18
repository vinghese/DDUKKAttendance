package edu.ddukk.ddukkattendance.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TimeTableDAO {

    @Query("Select * from TimeTableView")
    suspend fun getTimetableByDay(day: String)


}