package uz.bdm.dummyapiclient.model

data class CommentModel(
    val id: String,
    val message: String,
    val owner: UserModel,
    val post: String,
    val publishDate: String
)