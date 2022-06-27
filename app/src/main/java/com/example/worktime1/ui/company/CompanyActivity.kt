package com.example.worktime1.ui.company

import android.content.Intent
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.CompanyEvent
import com.example.worktime1.databinding.ActivityCompanyBinding
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.ui.scan.ScanActivity
import com.example.worktime1.utils.PrefsHelper
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CompanyActivity : BaseActivity<CompanyViewModel, ActivityCompanyBinding>(CompanyViewModel::class), ClickListener {

    private lateinit var adapter: CompanyAdapter

    override fun getViewBinding() = ActivityCompanyBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = CompanyViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupRecyclerView()
        setupListener()
        setupSwipeRefresh()
    }

    private fun setupListener() {
        binding.txtCompany.setOnClickListener {
            startActivity(Intent(this@CompanyActivity, ScanActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = CompanyAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCompany()
        }

        binding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_green_light
        )
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when(it) {
                is CompanyEvent.CompanyFetched -> it.array?.let {
                    viewModel.company = it
                    adapter.addItems(it)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        })
    }

    override fun onItemListener(item: CompanyModel) {
        val intent = Intent(this, ScanActivity::class.java)
        startActivity(intent)
    }
}