package edu.ddukk.ddukkattendance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ddukk.ddukkattendance.models.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from user")
    suspend fun getUsers(): List<User>

    @Query("select * from user where id = :userId")
    suspend fun getUserById(userId: Int): User

    @Query("Select * from User where email LIKE :email")
    suspend fun getUsersByEmail(email: String): User


}