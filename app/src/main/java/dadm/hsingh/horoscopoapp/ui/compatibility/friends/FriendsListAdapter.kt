package dadm.hsingh.horoscopoapp.ui.compatibility.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dadm.hsingh.horoscopoapp.databinding.FriendItemBinding
import dadm.hsingh.horoscopoapp.domain.model.Friend

class FriendsListAdapter(private val onItemClick: (String) -> Unit) : ListAdapter<Friend, FriendsListAdapter.ViewHolder>(FriendDiff){

    class ViewHolder(val onItemClick: (String) -> Unit, private val binding: FriendItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend){
            binding.nameItem.text = friend.name
            binding.dateBirthItem.text = friend.dateBirth.toString()
            binding.placeBirthItem.text = friend.placeBirth
            binding.userPhoto.setImageResource(friend.zodiacSign)
        }

        init {
            /*
            binding.root.setOnClickListener {
                onItemClick(binding.authorItem.text.toString())
            }

             */
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(onItemClick, FriendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object FriendDiff : DiffUtil.ItemCallback<Friend>(){
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }

}
