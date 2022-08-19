package uz.bdm.dummyapiclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.bdm.dummyapiclient.databinding.CommentItemLayoutBinding
import uz.bdm.dummyapiclient.model.CommentModel

class CommentAdapter(var items: List<CommentModel>) :
    RecyclerView.Adapter<CommentAdapter.ItemHolder>() {

    inner class ItemHolder(var binding: CommentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            CommentItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = items[position]

        holder.binding.tvComment.text = item.message
        holder.binding.tvDate.text = item.publishDate
        holder.binding.tvUser.text = "${item.owner.firstName} ${item.owner.lastName}"

        Glide.with(holder.itemView.context).load(item.owner.picture).into(holder.binding.imageUser)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}