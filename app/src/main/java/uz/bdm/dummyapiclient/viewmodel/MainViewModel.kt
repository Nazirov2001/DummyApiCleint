package uz.bdm.dummyapiclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.bdm.dummyapiclient.database.AppDatabase
import uz.bdm.dummyapiclient.model.CommentModel
import uz.bdm.dummyapiclient.model.PostModel
import uz.bdm.dummyapiclient.model.UserInfoModel
import uz.bdm.dummyapiclient.model.UserModel
import uz.bdm.dummyapiclient.viewmodel.repository.ApiRepository


class MainViewModel : ViewModel() {

    var repository = ApiRepository()

    private var _userData = MutableLiveData<List<UserModel>>()
    val userData: LiveData<List<UserModel>>
        get() {
            return _userData
        }

    private var _postData = MutableLiveData<List<PostModel>>()
    val postData: LiveData<List<PostModel>>
        get() {
            return _postData
        }

    private var _commentData = MutableLiveData<List<CommentModel>>()
    val commentData: LiveData<List<CommentModel>>
        get() {
            return _commentData
        }

    private var _userInfoData = MutableLiveData<UserInfoModel>()
    val userInfoData: LiveData<UserInfoModel>
        get() {
            return _userInfoData
        }

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() {
            return _error
        }

    private var _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() {
            return _progress
        }

    fun loadPosts() {
        repository.loadPosts(_error, _postData, _progress)
    }

    fun loadUsers() {
        repository.loadUsers(_error, _userData)
    }

    fun loadUserInfo(id: String) {
        repository.loadUserInfo(id, _error, _userInfoData)
    }

    fun loadUserPosts(id: String) {
        repository.loadUserPost(id, _error, _postData, _progress)
    }

    fun loadComments(id: String) {
        repository.loadComments(id, _error, _commentData, _progress)
    }

    fun insertPost2DB(items: List<PostModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            AppDatabase.getDatabase().getPostDao().deleteAll()
            AppDatabase.getDatabase().getPostDao().insertAll(items)
        }
    }

    fun insertUsers2DB(items: List<UserModel>) {
        CoroutineScope(Dispatchers.IO).launch {

            AppDatabase.getDatabase().getUserDao().deleteAll()
            AppDatabase.getDatabase().getUserDao().insertAll(items)
        }
    }

    fun getUsersDB() {
        CoroutineScope(Dispatchers.Main).launch {
            _userData.value =
                withContext(Dispatchers.IO) { AppDatabase.getDatabase().getUserDao().getUsers() }
        }
    }

    fun getPostsDB() {
        CoroutineScope(Dispatchers.Main).launch {
            _postData.value =
                withContext(Dispatchers.IO) { AppDatabase.getDatabase().getPostDao().getPosts() }
        }
    }
}