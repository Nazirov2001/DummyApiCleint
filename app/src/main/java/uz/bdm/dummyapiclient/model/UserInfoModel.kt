package uz.bdm.dummyapiclient.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "user_info")
class UserInfoModel(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val phone: String,
    val registerDate: String,
    val updatedDate: String
) {
    @Ignore
    public val location: LocationModel? = null
}