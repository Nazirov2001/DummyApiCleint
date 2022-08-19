package uz.bdm.dummyapiclient.screen.postdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import uz.bdm.dummyapiclient.adapter.CommentAdapter
import uz.bdm.dummyapiclient.databinding.ActivityPostDetailBinding
import uz.bdm.dummyapiclient.model.PostModel
import uz.bdm.dummyapiclient.viewmodel.MainViewModel

class PostDetailActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityPostDetailBinding
    lateinit var item: PostModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.swipe.setOnRefreshListener {
            loadData()
        }

        viewModel.progress.observe(this, Observer {
            binding.swipe.isRefreshing = it
        })

        viewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        viewModel.commentData.observe(this, Observer {
            binding.recyclerComment.layoutManager = LinearLayoutManager(this)
            binding.recyclerComment.adapter = CommentAdapter(it)
        })

        item = intent.getSerializableExtra("post_extra") as PostModel

        binding.author.text = "${item.owner?.firstName ?: ""} ${item.owner?.lastName ?: ""}"
        binding.tvTitle.text = item.text
        binding.publishDate.text = item.publishDate

        Glide.with(this).load(item.image).into(binding.imgPost)
        Glide.with(this).load(item.owner?.picture ?: "").into(binding.imgUser)
        loadData()
    }

    fun loadData() {
        viewModel.loadComments(item.id)
    }
}