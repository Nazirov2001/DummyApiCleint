package uz.bdm.dummyapiclient.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.bdm.dummyapiclient.model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<UserModel>)

    @Query("select * from users")
    fun getUsers(): List<UserModel>

    @Query("delete from users")
    fun deleteAll()
}