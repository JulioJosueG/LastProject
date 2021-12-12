package com.example.lastproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastproject.R
import com.example.lastproject.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowViewModel (): ViewModel() {

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    private val _select : MutableLiveData<TvShow> = MutableLiveData()
    private val _exception = MutableLiveData<Throwable>()
    private val _pagination : MutableLiveData<Pagination> = MutableLiveData()
    private val _loading : MutableLiveData<Boolean> = MutableLiveData()

    val service = ApiService.getInstance()
    var TypeInt : Int =0;
    val tvShowList : LiveData<List<TvShow>> = _tvShowList
    val selected : LiveData<TvShow> = _select
    val exception : LiveData<Throwable> = _exception
    val pagination : LiveData<Pagination> = _pagination
    val loading : LiveData<Boolean> = _loading


    fun loadTvShows(page: Int =1) {
        _loading.value = true;
        TypeInt = 0;
        var response = service.GetMostPopular(page).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                val responseBody = response.body()
                _tvShowList.value = responseBody!!.tv_shows
                _pagination.value = Pagination(
                    responseBody.page,
                    responseBody.totals,
                    responseBody.pages
                )
                _loading.value = false;

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

                _exception.value = t
                _loading.value = false;

            }

        })

    }

    fun select(tvShow: TvShow ){

    _select.value = tvShow

    }


    fun loadTVShowDetail(q : String){
        _loading.value = true;
        TypeInt +=1;

        var response = service.GetTvShow(q).enqueue(object : Callback<ApiDetailResponse> {
            override fun onResponse(call: Call<ApiDetailResponse>,
                                    response: Response<ApiDetailResponse>) {
                val responseBody = response.body()
                select(responseBody!!.tvShow)
                _loading.value = false;

            }

            override fun onFailure(call: Call<ApiDetailResponse>, t: Throwable) {
                _exception.value = t
                _loading.value = false;

            }

        })


    }

    fun search(string : String, page: Int =1){
        TypeInt = 1;
        _loading.value = true;

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.SearchTvShow(string,page).execute();
            var responseBody = response.body();
            _tvShowList.postValue(responseBody!!.tv_shows)
            _pagination.postValue(Pagination(
                responseBody.page,
                responseBody.totals,
                responseBody.pages

            ));
            _loading.postValue(false)

        }
    }
}