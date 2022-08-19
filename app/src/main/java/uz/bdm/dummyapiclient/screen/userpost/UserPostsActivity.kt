package uz.bdm.dummyapiclient.screen.userpost

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.bdm.dummyapiclient.adapter.PostAdapter
import uz.bdm.dummyapiclient.adapter.PostAdapterListener
import uz.bdm.dummyapiclient.adapter.UserInfoAdapter
import uz.bdm.dummyapiclient.databinding.ActivityUserPostsBinding
import uz.bdm.dummyapiclient.model.PostModel
import uz.bdm.dummyapiclient.model.UserModel
import uz.bdm.dummyapiclient.screen.postdetail.PostDetailActivity
import uz.bdm.dummyapiclient.viewmodel.MainViewModel

class UserPostsActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityUserPostsBinding
    lateinit var item: UserModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        item = intent.getSerializableExtra("extra") as UserModel

        binding.userName.text = "${item.firstName} ${item.lastName}"

        binding.swipe.setOnRefreshListener {
            loadData()
        }

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })

        viewModel.userInfoData.observe(this, Observer {
            binding.recyclerInfo.layoutManager = LinearLayoutManager(this)
            binding.recyclerInfo.adapter = UserInfoAdapter(it)
        })

        viewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.postData.observe(this, Observer {
            binding.recyclerPost.layoutManager = LinearLayoutManager(this)
            binding.recyclerPost.adapter = PostAdapter(it,
                object : PostAdapterListener {
                    override fun onClick(item: PostModel) {
                        startActivity(
                            Intent(
                                this@UserPostsActivity,
                                PostDetailActivity::class.java
                            ).putExtra("post_extra", item)
                        )
                    }

                })
        })

        loadData()

    }

    fun loadData() {
        viewModel.loadUserPosts(item.id)
        viewModel.loadUserInfo(item.id)
    }

}