package com.example.worktime1.ui

import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityAuthBinding
import com.example.worktime1.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding>(AuthViewModel::class) {

    override fun getViewBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
        binding.btnEnter.setOnClickListener {

        }
    }

    override fun subscribeToLiveData() {

    }
}