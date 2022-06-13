package com.example.worktime1.ui.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import com.example.worktime1.utils.Constants

class ScannerViewActivity : Activity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.cleanResult()
    }

    override fun onBackPressed() {
        this.cleanResult()
    }

    private fun cleanResult() {
        try {
            mScannerView!!.stopCamera()
        } catch (e: Exception) {
            e.message?.let { Log.e(Constants.ERROR, it) }
        }

        val resultIntent = Intent()
        resultIntent.putExtra(Constants.BAR_CODE, "")
        setResult(2, resultIntent)
        finish()
    }

    override fun handleResult(rawResult: Result) {
        Log.e(Constants.HANDLER, rawResult.text)
        Log.e(Constants.HANDLER, rawResult.barcodeFormat.toString())

        try {
            mScannerView!!.stopCamera()

            val resultIntent = Intent()

            resultIntent.putExtra(Constants.BAR_CODE, rawResult.text)
            setResult(2, resultIntent)
            finish()

            println(Constants.STOP_CAMERA)
        } catch (e: Exception) {
            e.message?.let { Log.e(Constants.ERROR, it) }
        }
    }
}
