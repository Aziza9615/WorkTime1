package com.example.worktime1.ui.company

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worktime1.base.BaseViewHolder
import com.example.worktime1.databinding.ItemCompanyListBinding
import com.example.worktime1.model.CompanyModel
import kotlinx.android.synthetic.main.item_company_list.view.*

class CompanyAdapter(private val listener: ClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<CompanyModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemCompanyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun addItems(item: MutableList<CompanyModel>) {
        items = item
        notifyDataSetChanged()
    }

    fun addItem(item: CompanyModel) {
        items.add(item)
        notifyDataSetChanged()
    }

    class ListViewHolder(var binding: ItemCompanyListBinding): BaseViewHolder(binding.root){
        fun bind(item: CompanyModel) {
            itemView.company.text = item.company
        }
    }
}
interface ClickListener {
    fun onItemListener(item: CompanyModel)
}