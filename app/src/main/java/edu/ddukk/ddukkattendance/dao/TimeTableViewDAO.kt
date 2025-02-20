package edu.ddukk.ddukkattendance.dao

import androidx.room.Dao
import androidx.room.Query
import edu.ddukk.ddukkattendance.models.TimeTableView

@Dao
interface TimeTableViewDAO {

    @Query("Select * from TimeTableView where day_of_week=:day")
    suspend fun getTimetableByDay(day: String): List<TimeTableView>


}