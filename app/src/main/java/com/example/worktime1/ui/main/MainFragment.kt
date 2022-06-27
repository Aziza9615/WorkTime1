package com.example.worktime1.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseFragment
import com.example.worktime1.base.MainEvent
import com.example.worktime1.databinding.FragmentMainBinding
import com.example.worktime1.model.MainModel
import com.example.worktime1.ui.scan.ScanActivity
import com.example.worktime1.utils.PrefsHelper
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(MainViewModel::class), ClickListener {

    private lateinit var adapter: MainAdapter
    private lateinit var preferences: PrefsHelper

    override fun attachBinding(
        list: MutableList<FragmentMainBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentMainBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        preferences = PrefsHelper(requireContext())
        viewModel = getViewModel(clazz = MainViewModel::class)
        viewModel.fetchMain()
        setupRecyclerView()
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.arrowTxt.setOnClickListener {
            val intent = Intent(requireContext(), ScanActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        binding.time.setOnClickListener {
            showDataRangePicker()
        }
    }

    private fun showDataRangePicker() {
        PrefsHelper.instance.saveDate("")
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .build()
        getFragmentManager()?.let {
            dateRangePicker.show(it, "datePicker")
        }

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->
            val startDate = dateSelected.first
            val endDate = dateSelected.second
            PrefsHelper.instance.saveDate("")
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
                is MainEvent.MainFetched -> it.array?.let {
                    viewModel.list = it
                    adapter.addItems(it)
                }
            }
        })
    }

    override fun onItemListener(item: MainModel) {}
}
