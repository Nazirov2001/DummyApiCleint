package uz.bdm.dummyapiclient.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.bdm.dummyapiclient.api.ApiService
import uz.bdm.dummyapiclient.model.*

class ApiRepository {

    var compositeDisposable = CompositeDisposable()

    fun loadPosts(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<PostModel>>,
        progress: MutableLiveData<Boolean>
    ) {
        compositeDisposable.add(
            ApiService.apiClient().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableObserver<BaseResponse<List<PostModel>>>() {
                    override fun onNext(t: BaseResponse<List<PostModel>>) {
                        success.value = t.data
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    fun loadUsers(error: MutableLiveData<String>, success: MutableLiveData<List<UserModel>>) {
        compositeDisposable.add(
            ApiService.apiClient().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<UserModel>>>() {
                    override fun onNext(t: BaseResponse<List<UserModel>>) {
                        success.value = t.data
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    fun loadUserInfo(
        id: String,
        error: MutableLiveData<String>,
        success: MutableLiveData<UserInfoModel>
    ) {
        compositeDisposable.add(
            ApiService.apiClient().getUserInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<UserInfoModel>() {
                    override fun onNext(t: UserInfoModel) {
                        success.value = t
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    fun loadUserPost(
        id: String,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<PostModel>>,
        progress: MutableLiveData<Boolean>
    ) {
        compositeDisposable.add(
            ApiService.apiClient().getUserPosts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableObserver<BaseResponse<List<PostModel>>>() {
                    override fun onNext(t: BaseResponse<List<PostModel>>) {
                        success.value = t.data
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }

    fun loadComments(
        id: String,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<CommentModel>>,
        progress: MutableLiveData<Boolean>
    ) {
        compositeDisposable.add(
            ApiService.apiClient().getComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CommentModel>>>() {
                    override fun onNext(t: BaseResponse<List<CommentModel>>) {
                        success.value = t.data
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }

                })
        )
    }
}