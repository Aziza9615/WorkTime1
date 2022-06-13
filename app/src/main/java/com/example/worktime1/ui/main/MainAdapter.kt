package com.example.worktime1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.worktime1.base.BaseViewHolder
import com.example.worktime1.databinding.ItemListBinding
import com.example.worktime1.model.MainData
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val listener: ClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<MainData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as ListViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemListener(item)
        }
    }

    fun addItems(item: MutableList<MainData>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: MainData) {
        items.add(item)
        notifyDataSetChanged()
    }

    class ListViewHolder(var binding: ItemListBinding): BaseViewHolder(binding.root){
        fun bind(item: MainData) {
            itemView.date.text = item.date.toString()
            itemView.being_late.text = item.time.toString()
            itemView.time.text = item.time.toString()
        }
    }
}
interface ClickListener {
    fun onItemListener(item: MainData)
}
