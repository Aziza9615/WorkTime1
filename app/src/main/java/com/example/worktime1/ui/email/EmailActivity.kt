package com.example.worktime1.ui.email

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.example.worktime1.R
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
        emailFocusListener()
    }

    private fun setupListener() {
        binding.etEmail.addTextChangedListener(loginTextWatcher)
        binding.btnEnter.setOnClickListener {
            email()
            startActivity(Intent(this, ConfirmActivity::class.java))
            finish()
        }
    }

    private fun email() {
        viewModel.email(email = binding.etEmail.text.toString())
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val email = binding.etEmail.text.toString().trim()
            binding.btnEnter.isEnabled = email.isNotEmpty()
                submitForm()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun submitForm() {
        binding.emailContainer.helperText = validEmail()

        val validEmail = binding.emailContainer.helperText == null

        if (validEmail)
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
    }

    private fun emailFocusListener() {
        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.etEmail.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Введите правильный email"
        }
        return null
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



