package uz.bdm.dummyapiclient.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "post_table")
class PostModel(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val id: String,
    val image: String,
    val likes: Int,
    val text: String,
//    val tags: List<String>,
    val publishDate: String,

    ) : Serializable {
    @Ignore
    val owner: UserModel? = null
}