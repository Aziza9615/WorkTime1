package com.example.worktime1.repository

import androidx.lifecycle.MutableLiveData
import com.example.worktime1.api.AuthApi
import com.example.worktime1.model.AuthModel
import com.example.worktime1.model.TokenModel
import com.example.worktime1.network.ResponseResult
import com.example.worktime1.utils.PrefsHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface AuthRepository {
    fun login(username: String): MutableLiveData<ResponseResult<TokenModel>>
    fun refreshToken(): MutableLiveData<ResponseResult<String>>
}

class AuthRepositoryImpl(private val api: AuthApi, private val preferences: PrefsHelper) :
    AuthRepository {

    override fun refreshToken(): MutableLiveData<ResponseResult<String>> {
        val result = MutableLiveData<ResponseResult<String>>(ResponseResult.loading())
        api.refreshToken(TokenModel(refreshToken = preferences.getRefreshToken()))
            .enqueue(object : Callback<TokenModel> {
                override fun onFailure(call: Call<TokenModel>, t: Throwable) {}

                override fun onResponse(
                    call: Call<TokenModel>,
                    response: retrofit2.Response<TokenModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {

                        val token = response.body()?.accessToken ?: ""
                        token.let { preferences.saveAccessToken(it) }
                        result.value = ResponseResult.success(preferences.getRefreshToken())
                    } else {
                        result.value = ResponseResult.error(response.message())
                    }
                }
            })
        return result
    }

    override fun login(
        username: String
    ): MutableLiveData<ResponseResult<TokenModel>> {
        val map = mapOf(
            "username" to username
        )
        val data: MutableLiveData<ResponseResult<TokenModel>> =
            MutableLiveData(ResponseResult.loading())
        api.login(map).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
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

