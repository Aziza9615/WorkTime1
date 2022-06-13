package com.example.worktime1.ui

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.worktime1.R
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.BaseEvent
import com.example.worktime1.base.BaseViewModel
import com.example.worktime1.base.ProfileEvent
import com.example.worktime1.databinding.ActivityAuthBinding
import com.example.worktime1.databinding.ActivityMainBinding
import com.example.worktime1.utils.PrefsHelper
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AuthActivity : BaseActivity<AuthViewModel, ActivityAuthBinding>(AuthViewModel::class) {

    private lateinit var preferences: PrefsHelper

    override fun getViewBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override fun setupViews() {
        preferences = PrefsHelper(this)
        viewModel = getViewModel(clazz = AuthViewModel::class)
        setupListener()
    }

    private fun login() {
        viewModel.login(email = binding.etEmail.text.toString())
    }

    private fun setupListener() {
        binding.btnEnter.setOnClickListener { login() }
        binding.btnEnter.setOnClickListener {
            startActivity(Intent(this@AuthActivity, ConfirmActivity::class.java))
        }
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is ProfileEvent.UserIsStuffFetched -> {
                    if (it.item.isStuff == true) {
                        preferences.saveIsStuff(true)
                        startActivity(Intent(this, ConfirmActivity::class.java))
                    }
                }
            }
            viewModel.error.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            })
        })
    }
}