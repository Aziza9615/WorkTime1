package com.example.worktime1.ui

import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityMainBinding
import com.example.worktime1.databinding.ActivityWebBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WebActivity : BaseActivity<AuthViewModel, ActivityWebBinding>(AuthViewModel::class) {

    override fun getViewBinding() = ActivityWebBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.txtCompany.setOnClickListener {

        }
    }

    override fun subscribeToLiveData() {

    }
}