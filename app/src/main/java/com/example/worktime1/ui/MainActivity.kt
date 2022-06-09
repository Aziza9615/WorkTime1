package com.example.worktime1.ui

import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<AuthViewModel, ActivityMainBinding>(AuthViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
    }

    override fun subscribeToLiveData() {

    }
}