package com.example.contacttracingproject.stats

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.contacttracingproject.R
import com.example.contacttracingproject.databinding.FragmentStatisticsBinding
import com.example.retrofitcovidstatistics.network.RetrofitApi
import com.example.retrofitcovidstatistics.network.RetrofitClient
import kotlinx.coroutines.*

// Statistics fragment kotlin
class Statistics : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchApi()
        fetchMalaysiaApi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)
        return binding.root

    }

    companion object {
        fun newInstance() = Statistics()
    }

    private fun fetchApi() {
        var api: RetrofitApi? = null
        api = RetrofitClient.getInstance()?.getApi()

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("RETROFIT ERROR", throwable.message!!)
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = api?.getStatistics()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    binding?.statisticsCasesGlobal.text = response.body()?.cases.toString()
                    Log.i("RETROFIT", response.body()?.deaths.toString())
                    binding?.statisticsGlobalDeaths?.text = response.body()?.deaths.toString()
                    binding?.statisticsGlobalRecovered?.text = response.body()?.recovered.toString()
                }
            }
        }
    }

    private fun fetchMalaysiaApi() {
        var api: RetrofitApi? = null
        api = RetrofitClient.getInstance()?.getApi()

        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.e("RETROFIT ERROR", throwable.message!!)
        }

        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = api?.getMalaysia()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    binding?.statisticsCasesLocal?.text = response.body()?.cases.toString()
                    Log.i("RETROFIT", response.body()?.deaths.toString())
                    binding?.statisticsLocalDeaths?.text = response.body()?.deaths.toString()
                    binding?.statisticsLocalRecovered?.text = response.body()?.recovered.toString()
                }
            }
        }
    }
}

