package uz.bdm.dummyapiclient.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.bdm.dummyapiclient.model.UserInfoModel

@Dao
interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: UserInfoModel)

    @Query("select * from user_info")
    suspend fun getUserInfo(): UserInfoModel

    @Query("delete from user_info")
    fun deleteAll()
}