package com.example.worktime1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>(BaseViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = BaseViewModel::class)
    }

    override fun subscribeToLiveData() {
    }
}