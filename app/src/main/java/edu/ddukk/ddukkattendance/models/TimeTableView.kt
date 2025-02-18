package edu.ddukk.ddukkattendance.models

import androidx.room.DatabaseView


@DatabaseView(
    "select timetable.table_id," +
            "timetable.day_of_week," +
            "timetable.start_time," +
            "timetable.end_time," +
            "timetable.period," +
            "subjects.subject_name," +
            "instructors.instructor_name " +
            "from timetable inner join subjects on " +
            "timetable.subject_id = subjects.subject_id " +
            "inner join instructors on " +
            "instructors.instructor_id = " +
            "timetable.instructor_id"
)
data class TimeTableView(
    val table_id: Int,
    val period: String,
    val subject_name: String,
    val start_time: String,
    val end_time: String,
    val instructor_name: String,
    val day_of_week: String
)
