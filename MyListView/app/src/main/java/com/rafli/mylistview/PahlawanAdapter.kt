package com.rafli.mylistview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.rafli.mylistview.Pahlawan
import com.rafli.mylistview.R

class PahlawanAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Pahlawan>()

    override fun getCount(): Int = heroes.size

    override fun getItem(i: Int): Any = heroes[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_pahlawan, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val hero = getItem(position) as Pahlawan
        viewHolder.bind(hero)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtDescription: TextView = view.findViewById(R.id.txt_description)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)

        internal fun bind(hero: Pahlawan) {
            txtName.text = hero.name
            txtDescription.text = hero.description
            imgPhoto.setImageResource(hero.photo)
        }
    }
}