package com.example.worktime1.ui

import android.content.Intent
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityMainBinding
import com.example.worktime1.databinding.ActivityWebBinding
import com.example.worktime1.ui.scan.ScanActivity
import com.example.worktime1.utils.PrefsHelper
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WebActivity : BaseActivity<AuthViewModel, ActivityWebBinding>(AuthViewModel::class) {

    override fun getViewBinding() = ActivityWebBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupListener()
    }

    private fun setupListener() {
        binding.company.setOnClickListener {
            startActivity(Intent(this@WebActivity, ScanActivity::class.java))
        }
    }

    override fun subscribeToLiveData() {

    }
}