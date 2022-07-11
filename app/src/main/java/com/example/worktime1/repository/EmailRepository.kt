package com.example.worktime1.repository

import androidx.lifecycle.MutableLiveData
import com.example.worktime1.api.EmailApi
import com.example.worktime1.model.ConfirmModel
import com.example.worktime1.model.EmailModel
import com.example.worktime1.network.ResponseResult
import com.example.worktime1.utils.PrefsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface EmailRepository {
    fun email(email: String): MutableLiveData<ResponseResult<EmailModel>>
    fun code(pin_code: String): MutableLiveData<ResponseResult<ConfirmModel>>
}

class EmailRepositoryImpl(private val api: EmailApi) :
    EmailRepository {

    override fun email(email: String): MutableLiveData<ResponseResult<EmailModel>> {
        val map = mapOf("email" to email)
        val data: MutableLiveData<ResponseResult<EmailModel>> =
            MutableLiveData(ResponseResult.loading())
        api.fetchEmail(map).enqueue(object : Callback<EmailModel> {
            override fun onFailure(call: Call<EmailModel>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<EmailModel>, response: Response<EmailModel>) {
                when (response.code()) {
                    200 -> data.value = ResponseResult.success(response.body())
                    401 -> data.value =
                        ResponseResult.error("No active account found with the given credentials")
                }
            }

        })
        return data
    }

    override fun code(pin_code: String): MutableLiveData<ResponseResult<ConfirmModel>> {
        val map = mapOf("pin_code" to pin_code)
        val data: MutableLiveData<ResponseResult<ConfirmModel>> =
            MutableLiveData(ResponseResult.loading())
        api.code(map).enqueue(object : Callback<ConfirmModel> {
            override fun onFailure(call: Call<ConfirmModel>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<ConfirmModel>, response: Response<ConfirmModel>) {
                when (response.code()) {
                    200 -> data.value = ResponseResult.success(response.body())
                    401 -> data.value =
                        ResponseResult.error("No active account found with the given credentials")
                }
            }

        })
        return data
    }
}





