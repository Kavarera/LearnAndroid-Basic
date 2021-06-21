package com.rafli.submissiondicoding_rafli

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class ListFoodAdapter(val listFood: ArrayList<Food>) : RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvName:TextView = itemView.findViewById(R.id.item_name_tv)
        var tvDetails:TextView= itemView.findViewById(R.id.item_detail_tv)
        var imgPhoto:ImageView= itemView.findViewById(R.id.item_photo_img)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view:View= LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_food,viewGroup,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val food = listFood[position]
        Glide.with(holder.itemView.context)
            .load(food.photo)
            .apply(RequestOptions().override(100,100))
            .into(holder.imgPhoto)
        holder.tvName.text =food.name
        holder.tvDetails.text=food.detail
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listFood[holder.adapterPosition])}
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback= onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:Food)
    }



}