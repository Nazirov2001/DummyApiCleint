package uz.bdm.dummyapiclient.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
) : Serializable