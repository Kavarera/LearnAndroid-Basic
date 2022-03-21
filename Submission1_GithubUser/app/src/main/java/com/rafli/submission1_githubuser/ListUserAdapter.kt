package com.rafli.submission1_githubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class ListUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallBack: OnItemClickCallBack


    fun setOnItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack=onItemClickCallBack
    }

    inner class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var civPhoto: CircleImageView  = itemView.findViewById(R.id.civ_photo)
        var tv_nameGit : TextView = itemView.findViewById(R.id.tv_nameGit)
        var tv_unameGit: TextView = itemView.findViewById(R.id.tv_unameGit)
        var tv_kotaGit: TextView = itemView.findViewById(R.id.tv_kotaGit)
        var btnFavorited: Button = itemView.findViewById(R.id.btn_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        Glide.with(holder.itemView.context)
            .load(user.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.civPhoto)
        holder.tv_nameGit.text=user.nama
        holder.tv_unameGit.text=user.username?.toLowerCase()
        holder.tv_kotaGit.text=user.lokasi
        holder.btnFavorited.setOnClickListener {

            if (user.favorited=="false"){
                holder.btnFavorited.setBackgroundResource(R.drawable.ic_heart_filled)
                user.favorited="true"
                Toast.makeText(holder.itemView.context, "Favorited", Toast.LENGTH_SHORT).show()
            }
            else if(user.favorited=="true"){
                holder.btnFavorited.setBackgroundResource(R.drawable.ic_heart)
                user.favorited="false"
                Toast.makeText(holder.itemView.context, "Unfavorited", Toast.LENGTH_SHORT).show()

            }
        }
        
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, user.nama, Toast.LENGTH_SHORT).show()
            onItemClickCallBack.onItemClicked(listUser[holder.adapterPosition])
        }
        
    }
    interface OnItemClickCallBack{
        fun onItemClicked(data: User)
    }

    override fun getItemCount(): Int = listUser.size

}