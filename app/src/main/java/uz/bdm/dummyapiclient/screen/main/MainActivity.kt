package uz.bdm.dummyapiclient.screen.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import uz.bdm.dummyapiclient.adapter.PostAdapter
import uz.bdm.dummyapiclient.adapter.PostAdapterListener
import uz.bdm.dummyapiclient.adapter.UserAdapter
import uz.bdm.dummyapiclient.adapter.UserAdapterListener
import uz.bdm.dummyapiclient.databinding.ActivityMainBinding
import uz.bdm.dummyapiclient.model.PostModel
import uz.bdm.dummyapiclient.model.UserModel
import uz.bdm.dummyapiclient.screen.postdetail.PostDetailActivity
import uz.bdm.dummyapiclient.screen.userpost.UserPostsActivity
import uz.bdm.dummyapiclient.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.swipe.setOnRefreshListener { loadData() }

        viewModel.progress.observe(this, Observer { binding.swipe.isRefreshing = it })

        viewModel.postData.observe(this, Observer {

            viewModel.insertPost2DB(it)
            binding.postRecycler.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
            binding.postRecycler.adapter = PostAdapter(it, object : PostAdapterListener {

                override fun onClick(item: PostModel) {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            PostDetailActivity::class.java
                        ).putExtra("post_extra", item)
                    )
                }

            })

        })

        viewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.userData.observe(this, Observer {

            viewModel.insertUsers2DB(it)
            binding.userRecycler.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.userRecycler.adapter = UserAdapter(it, object : UserAdapterListener {

                override fun onClick(item: UserModel) {
                    startActivity(
                        Intent(this@MainActivity, UserPostsActivity::class.java).putExtra(
                            "extra",
                            item
                        )
                    )
                }

            })

        })

        loadData()
    }

    fun loadData() {

        viewModel.loadPosts()
        viewModel.loadUsers()
        viewModel.getPostsDB()
        viewModel.getUsersDB()

    }
}