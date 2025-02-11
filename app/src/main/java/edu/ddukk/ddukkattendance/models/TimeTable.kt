package edu.ddukk.ddukkattendance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "timetable",
    foreignKeys = [
        ForeignKey(
            entity = Subjects::class,
            parentColumns = ["subject_id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Instructors::class,
            parentColumns = ["instructor_id"],
            childColumns = ["instructor_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Breaks::class,
            parentColumns = ["break_id"],
            childColumns = ["break_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Programs::class,
            parentColumns = ["programs_id"],
            childColumns = ["programs_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Semesters::class,
            parentColumns = ["semester_id"],
            childColumns = ["semester_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["subject_id", "instructor_id", "programs_id", "break_id", "semester_id"])
    ]
)

data class TimeTable(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "table_id", index = true)
    val tableId: Int = 0,

    @ColumnInfo(name = "day_of_week")
    val dayOfWeek: String,  // Example: Monday, Tuesday, etc.

    @ColumnInfo(name = "start_time")
    val startTime: String,  // Format: HH:mm

    @ColumnInfo(name = "end_time")
    val endTime: String,    // Format: HH:mm

    @ColumnInfo(name = "period")
    val period: String,    // Format: 1, 2, 3

    @ColumnInfo(name = "programs_id", index = true)
    val programmeId: Int,  // Example: 1, 2, 3, etc.

    @ColumnInfo(name = "semester_id", index = true)
    val semesterId: Int,  // Example: 1, 2, 3, etc.

    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: String,

    @ColumnInfo(name = "instructor_id", index = true)
    val instructorId: Int? = null,

    @ColumnInfo(name = "break_id", index = true)
    val breakId: Int? = null
)

@Entity(tableName = "subjects")
data class Subjects(
    @PrimaryKey
    @ColumnInfo(name = "subject_id", index = true)
    val subjectId: Int = 0,

    @ColumnInfo(name = "subject_code", defaultValue = "23-493-")
    val subject_code: String,

    @ColumnInfo(name = "subject_name")
    val subjectName: String
)

@Entity(tableName = "instructors")
data class Instructors(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "instructor_id", index = true)
    val instructorId: Int = 0,

    @ColumnInfo(name = "instructor_name")
    val instructorName: String
)

@Entity(tableName = "breaks")
data class Breaks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "break_id", index = true)
    val breakId: Int = 0,

    @ColumnInfo(name = "break_type")
    val breakType: String,

    @ColumnInfo(name = "break_start_time")
    val breakStartTime: String,  // Format: HH:mm

    @ColumnInfo(name = "break_end_time")
    val breakEndTime: String  // Format: HH:mm
)

@Entity(tableName = "programs")
data class Programs(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "programs_id", index = true)
    val programsId: Int = 0,

    @ColumnInfo(name = "programs_code", index = true)
    val programsCode: String,

    @ColumnInfo(name = "programs_name")
    val programeName: String,

    @ColumnInfo(name = "number_of_semesters")
    val numberOfSemesters: Int


)

@Entity(tableName = "semesters")
data class Semesters(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "semester_id", index = true)
    val programsId: Int = 0,

    @ColumnInfo(name = "semester_name")
    val programeName: String
)