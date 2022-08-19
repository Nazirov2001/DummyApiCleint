package uz.bdm.dummyapiclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.bdm.dummyapiclient.databinding.UserItemLayoutBinding
import uz.bdm.dummyapiclient.model.UserModel

interface UserAdapterListener {
    fun onClick(item: UserModel)
}

class UserAdapter(var items: List<UserModel>, val adapterListener: UserAdapterListener) :
    RecyclerView.Adapter<UserAdapter.ItemHolder>() {

    inner class ItemHolder(var binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        var item = items[position]

        holder.binding.nameUser.text = item.firstName

        Glide.with(holder.itemView.context).load(item.picture).into(holder.binding.imgUser)

        holder.itemView.setOnClickListener {
            adapterListener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}