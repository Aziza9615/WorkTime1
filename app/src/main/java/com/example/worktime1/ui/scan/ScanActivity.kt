package com.example.worktime1.ui.scan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.worktime1.base.BaseActivity
import com.example.worktime1.databinding.ActivityScanBinding
import com.example.worktime1.ui.AuthViewModel
import com.example.worktime1.ui.MainActivity
import com.example.worktime1.ui.WebActivity
import com.example.worktime1.utils.Constants
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ScanActivity : BaseActivity<AuthViewModel, ActivityScanBinding>(AuthViewModel::class) {

    private val ZXING_CAMERA_PERMISSION = 1
    private var mClss: Class<*>? = null

    override fun getViewBinding() = ActivityScanBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AuthViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.scan.setOnClickListener {
            launchActivity(ScannerViewActivity::class.java)
        }
        binding.txtCompany.setOnClickListener {
            startActivity(Intent(this@ScanActivity, WebActivity::class.java))
        }
        binding.btnStatistic.setOnClickListener {
            startActivity(Intent(this@ScanActivity, MainActivity::class.java))
        }
    }

     @SuppressLint("MissingSuperCall")
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         val barcode = data?.extras?.getString(Constants.BAR_CODE)

         if (barcode == "") {
             Toast.makeText(this@ScanActivity, Constants.BAR_CODE_NOT_FOUND, Toast.LENGTH_LONG)
                 .show()
         }
     }

    private fun launchActivity(clss: Class<*>) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            mClss = clss
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), ZXING_CAMERA_PERMISSION)
        } else {
            val intent = Intent(this, clss)
            startActivityForResult(intent, 2)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ZXING_CAMERA_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, Constants.PRESS_AGAIN_TO_SCAN, Toast.LENGTH_LONG).show()
                } else {
                }
                return
            }
        }
    }

    override fun subscribeToLiveData() {}
}