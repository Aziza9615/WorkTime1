package com.example.worktime1.ui.main

import com.example.worktime1.R
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        getSupportFragmentManager().beginTransaction()
            .add(R.id.activity, MainFragment ()).commit()
    }

    override fun subscribeToLiveData() {}
}