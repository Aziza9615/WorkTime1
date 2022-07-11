package com.example.worktime1.ui.company

import android.content.Intent
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityCompanyBinding
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.ui.scan.ScanActivity
import com.example.worktime1.utils.PrefsHelper
import kotlinx.android.synthetic.main.item_company_list.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CompanyActivity : BaseActivity<CompanyViewModel, ActivityCompanyBinding>(CompanyViewModel::class), ClickListener {

    private lateinit var adapter: CompanyAdapter

    override fun getViewBinding() = ActivityCompanyBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = CompanyViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupRecyclerView()
        setupSwipeRefresh()
    }

    private fun setupRecyclerView() {
        adapter = CompanyAdapter(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
        }

        binding.swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_green_light
        )
    }

    override fun subscribeToLiveData() {
        viewModel.company.observe(this, Observer {
            if (it != null) adapter.addItems(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCompany()
    }

    override fun onItemListener(item: CompanyModel) {
        val intent = Intent(this, ScanActivity::class.java)
        startActivity(intent)
    }
}