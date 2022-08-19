package uz.bdm.dummyapiclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.bdm.dummyapiclient.databinding.PostItemLayoutBinding
import uz.bdm.dummyapiclient.model.PostModel

interface PostAdapterListener {
    fun onClick(item: PostModel)
}

class PostAdapter(var items: List<PostModel>, val adapterListener: PostAdapterListener) :
    RecyclerView.Adapter<PostAdapter.ItemHolder>() {

    inner class ItemHolder(var binding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = items[position]

        holder.binding.tvUser.text = "${item.owner?.firstName ?: ""} ${item.owner?.lastName ?: ""}"
        holder.binding.tvDate.text = item.publishDate
        //  holder.binding.tvTitle.text = item.text
        holder.binding.likeCount.text = item.likes.toString() + " likes"

        Glide.with(holder.itemView.context).load(item.image).into(holder.binding.itemImage)
        Glide.with(holder.itemView.context).load(item.owner?.picture ?: 0)
            .into(holder.binding.imageUser)

        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }
}
