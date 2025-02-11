package edu.ddukk.ddukkattendance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "first_name")
    val firstName : String,
    @ColumnInfo(name="last_name")
    val lastName: String?,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "college_id")
    val collegeId: String,
    @ColumnInfo(name = "password")
    val password:String,
    @ColumnInfo(name="role")
    val role: String ="Student"

)
