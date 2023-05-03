package com.example.sqlkotin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlkotin.db.ListItem
import com.example.sqlkotin.db.MyIntentConstans

class MyAdapter(listMain: ArrayList<ListItem>, contextM: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
    var listArry = listMain
    var context = contextM

    class MyHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        var context = contextV
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        fun setData(item: ListItem){
            tvTitle.text = item.title
            itemView.setOnClickListener{
                val intent = Intent(context,Edit_Activity::class.java).apply {
                    putExtra(MyIntentConstans.I_TITLE_KEY, item.title)
                    putExtra(MyIntentConstans.I_DESK_KEY, item.desc)
                    putExtra(MyIntentConstans.I_URI_KEY, item.uri)

                }
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }


    override fun getItemCount(): Int {
        return listArry.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArry.get(position))
    }
    fun updateAdapter(listItems: List<ListItem>){
        listArry.clear()
        listArry.addAll(listItems)
        notifyDataSetChanged()
    }

}