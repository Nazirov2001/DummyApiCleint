package uz.bdm.dummyapiclient.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.bdm.dummyapiclient.model.PostModel

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<PostModel>)

    @Query("select * from post_table")
    fun getPosts(): List<PostModel>

    @Query("delete from post_table")
    fun deleteAll()
}