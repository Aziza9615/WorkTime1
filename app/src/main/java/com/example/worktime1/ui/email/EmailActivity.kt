package com.example.worktime1.ui.email

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.EmailEvent
import com.example.worktime1.databinding.ActivityAuthBinding
import com.example.worktime1.ui.confirm.ConfirmActivity
import com.example.worktime1.utils.PrefsHelper
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmailActivity : BaseActivity<EmailViewModel, ActivityAuthBinding>(EmailViewModel::class) {

    private lateinit var preferences: PrefsHelper

    override fun getViewBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override fun setupViews() {
        preferences = PrefsHelper(this)
        viewModel = getViewModel(clazz = EmailViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.btnEnter.setOnClickListener {
            email()
            startActivity(Intent(this, ConfirmActivity::class.java))
        }
    }

    private fun email() {
        viewModel.email(email = binding.etEmail.text.toString())
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is EmailEvent.EmailFetched -> {
                    startActivity(Intent(this, ConfirmActivity::class.java))
                    finish()
                }
            }
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.loading.observe(this, Observer {
            if (it) binding.progressBar.visibility = View.VISIBLE
            else binding.progressBar.visibility = View.GONE
        })
    }
}



