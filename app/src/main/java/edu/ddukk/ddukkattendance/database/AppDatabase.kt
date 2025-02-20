package edu.ddukk.ddukkattendance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.ddukk.ddukkattendance.dao.BreaksDAO
import edu.ddukk.ddukkattendance.dao.TimeTableViewDAO
import edu.ddukk.ddukkattendance.dao.UserDAO
import edu.ddukk.ddukkattendance.models.Breaks
import edu.ddukk.ddukkattendance.models.Instructors
import edu.ddukk.ddukkattendance.models.Programs
import edu.ddukk.ddukkattendance.models.Semesters
import edu.ddukk.ddukkattendance.models.Subjects
import edu.ddukk.ddukkattendance.models.TimeTable
import edu.ddukk.ddukkattendance.models.TimeTableView
import edu.ddukk.ddukkattendance.models.User

@Database(
    entities = [User::class, TimeTable::class, Subjects::class, Instructors::class,
        Semesters::class, Breaks::class, Programs::class],
    views = [TimeTableView::class],
    version = 8,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun breaksDAO(): BreaksDAO
    abstract fun timetableViewDAO(): TimeTableViewDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    name = "attendance-db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
    }
}