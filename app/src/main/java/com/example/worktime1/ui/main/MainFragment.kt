package com.example.worktime1.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseFragment
import com.example.worktime1.base.ListEvent
import com.example.worktime1.databinding.FragmentMainBinding
import com.example.worktime1.model.MainData
import com.example.worktime1.ui.scan.ScanActivity
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(MainViewModel::class), ClickListener {

    private lateinit var adapter: MainAdapter

    override fun attachBinding(
        list: MutableList<FragmentMainBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentMainBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        viewModel.fetchList()
        setupRecyclerView()
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
        }
        binding.arrowTxt.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
        }
        binding.time.setOnClickListener {
            showDataRangePicker()
        }
    }

    private fun showDataRangePicker() {
        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .build()

        getFragmentManager()?.let {
            dateRangePicker.show(it, "date_range_picker")
        }

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->
            val startDate = dateSelected.first
            val endDate = dateSelected.second
            if (startDate != null && endDate != null) {
                binding.view9.text = "${convertLongToTime(startDate)}"
                binding.view3.text = "${convertLongToTime(endDate)}"
            }
        }
    }
    
    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault())
        return format.format(date)
    }

    private fun setupRecyclerView() {
        adapter = MainAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is ListEvent.ListFetched -> it.array?.let {
                    viewModel.data = it
                    adapter.addItems(it)
                }
            }
        })
    }

    override fun onItemListener(item: MainData) {}
}
