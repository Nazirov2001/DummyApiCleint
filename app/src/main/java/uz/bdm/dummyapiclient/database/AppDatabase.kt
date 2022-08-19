package uz.bdm.dummyapiclient.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.bdm.dummyapiclient.model.PostModel
import uz.bdm.dummyapiclient.model.UserInfoModel
import uz.bdm.dummyapiclient.model.UserModel


@Database(entities = [UserModel::class, PostModel::class, UserInfoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
    abstract fun getUserDao(): UserDao
    abstract fun getUserInfoDao(): UserInfoDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun initDatabase(context: Context) {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "dummy_db"
                    ).build()
                }
            }
        }

        fun getDatabase(): AppDatabase {
            return INSTANCE!!
        }
    }
}