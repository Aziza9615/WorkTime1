package com.example.worktime1.repository

import androidx.lifecycle.MutableLiveData
import com.example.worktime1.api.CompanyApi
import com.example.worktime1.model.ArriveModel
import com.example.worktime1.model.CompanyModel
import com.example.worktime1.model.MainModel
import com.example.worktime1.network.ResponseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

interface CompanyRepository {
    fun fetchCompany(): MutableLiveData<ResponseResult<MutableList<CompanyModel>>>
    fun fetchMain(): MutableLiveData<ResponseResult<MutableList<MainModel>>>
    fun fetchArrive(): MutableLiveData<ResponseResult<MutableList<ArriveModel>>>
}

class CompanyRepositoryImpl(private val api: CompanyApi) : CompanyRepository {

    override fun fetchCompany(): MutableLiveData<ResponseResult<MutableList<CompanyModel>>> {
        val data: MutableLiveData<ResponseResult<MutableList<CompanyModel>>> =
            MutableLiveData(ResponseResult.loading())
        api.fetchCompany().enqueue(object : Callback<MutableList<CompanyModel>> {
            override fun onFailure(call: Call<MutableList<CompanyModel>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(
                call: Call<MutableList<CompanyModel>>,
                response: Response<MutableList<CompanyModel>>
            ) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun fetchMain(): MutableLiveData<ResponseResult<MutableList<MainModel>>> {
        val data: MutableLiveData<ResponseResult<MutableList<MainModel>>> =
            MutableLiveData(ResponseResult.loading())
        api.fetchMain().enqueue(object : Callback<MutableList<MainModel>> {
            override fun onFailure(call: Call<MutableList<MainModel>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(
                call: Call<MutableList<MainModel>>,
                response: Response<MutableList<MainModel>>
            ) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }

    override fun fetchArrive(): MutableLiveData<ResponseResult<MutableList<ArriveModel>>> {
        val data: MutableLiveData<ResponseResult<MutableList<ArriveModel>>> =
            MutableLiveData(ResponseResult.loading())
        api.fetchArrive().enqueue(object : Callback<MutableList<ArriveModel>> {
            override fun onFailure(call: Call<MutableList<ArriveModel>>, t: Throwable) {
                data.value = ResponseResult.error(t.message)
            }

            override fun onResponse(
                call: Call<MutableList<ArriveModel>>,
                response: Response<MutableList<ArriveModel>>
            ) {
                data.value = ResponseResult.success(response.body())
            }
        })
        return data
    }
}

