package uz.bdm.dummyapiclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.bdm.dummyapiclient.databinding.UserInfoLayoutBinding
import uz.bdm.dummyapiclient.model.UserInfoModel

class UserInfoAdapter(var item: UserInfoModel) :
    RecyclerView.Adapter<UserInfoAdapter.ItemHolder>() {

    inner class ItemHolder(var binding: UserInfoLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            UserInfoLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Glide.with(holder.itemView.context).load(item.picture).into(holder.binding.imgUser)

        holder.binding.tvGender.text = item.gender
        holder.binding.tvMail.text = item.email
        holder.binding.tvPhone.text = item.phone
    }

    override fun getItemCount(): Int {
        return 1
    }
}