package com.example.worktime1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worktime1.base.BaseViewHolder
import com.example.worktime1.databinding.ItemCompanyListBinding
import com.example.worktime1.databinding.ItemListBinding
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val listener: MainFragment) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListOf<MainModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainAdapter.ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val holder = holder as MainAdapter.ListViewHolder
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onItemListener(item)
        }
    }

    fun addItems(item: List<MainModel>) {
        items = item as MutableList<MainModel>
        notifyDataSetChanged()
    }

    class ListViewHolder(var binding: ItemListBinding): BaseViewHolder(binding.root){
        fun bind(item: MainModel) {
            itemView.date.text = item.day_of_week
            itemView.being_late.text = item.start_work
            itemView.time.text = item.finish_work
        }
    }
}

interface ClickListener {
    fun onItemListener(item: MainModel)
}
