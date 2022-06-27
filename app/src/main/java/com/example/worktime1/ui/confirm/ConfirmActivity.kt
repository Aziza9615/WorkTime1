package com.example.worktime1.ui.confirm

import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.base.CodeEvent
import com.example.worktime1.databinding.ActivityConfirmBinding
import com.example.worktime1.ui.company.CompanyActivity
import com.example.worktime1.ui.email.EmailActivity
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ConfirmActivity :
    BaseActivity<ConfirmViewModel, ActivityConfirmBinding>(ConfirmViewModel::class) {

    override fun getViewBinding() = ActivityConfirmBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ConfirmViewModel::class)
        onClick()
        countdown()
        val view = binding.smsCodeView
        view.isEnabled
        binding.nextBtn.isEnabled = false
        view.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->

            if (code.length < 6) {
                binding.nextBtn.isEnabled = false
            }

            if (isComplete) {
                binding.nextBtn.isEnabled = true
            }
        }
    }

    private fun onClick() {
        binding.nextBtn.setOnClickListener {
            if (binding.nextBtn.isEnabled)
                startActivity(Intent(this@ConfirmActivity, CompanyActivity::class.java))
        }
        binding.arrowTxt.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.arrow.setOnClickListener {
            onBackPressed()
            finish()
        }
        //binding.smsCodeView.addTextChangedListener(loginTextWatcher)
    }

    private fun countdown() {
        object : CountDownTimer(53000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.sendBtn.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val code = binding.sendBtn.toString().trim()
                binding.sendBtn.isEnabled = code.isNotEmpty()
                binding.sendBtn.text = "Отправить снова 00:53"
                binding.sendBtn.setOnClickListener {
                    startActivity(Intent(this@ConfirmActivity, EmailActivity::class.java))
                }
            }
        }.start()
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
            when (it) {
                is CodeEvent.CodeFetched -> {
                    startActivity(Intent(this, CompanyActivity::class.java))
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

