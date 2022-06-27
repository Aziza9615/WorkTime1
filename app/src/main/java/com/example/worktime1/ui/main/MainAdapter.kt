package com.example.worktime1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worktime1.base.BaseViewHolder
import com.example.worktime1.databinding.ItemListBinding
import com.example.worktime1.model.MainModel
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val listener: ClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<MainModel>()

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

    fun addItems(item: MutableList<MainModel>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: MainModel) {
        items.add(item)
        notifyDataSetChanged()
    }

    class ListViewHolder(var binding: ItemListBinding): BaseViewHolder(binding.root){
        fun bind(item: MainModel) {
            itemView.date.text = item.day.toString().trim()
            itemView.being_late.text = item.arrival_time.toString().trim()
            itemView.time.text = item.late.toString().trim()
        }
    }
}
interface ClickListener {
    fun onItemListener(item: MainModel)
}
