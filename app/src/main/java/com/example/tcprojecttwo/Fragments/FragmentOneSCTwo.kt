package com.example.tcprojecttwo.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.tcprojecttwo.DataClasses.PuneriTv
import com.example.tcprojecttwo.DataClasses.PuneriTvVideo
import com.example.tcprojecttwo.R
import com.example.tcprojecttwo.RetrofitInstance
import com.example.tcprojecttwo.databinding.FragmentOneBinding
import com.example.tcprojecttwo.databinding.FragmentOneSCTwoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentOneSCTwo : Fragment() {
    private lateinit var _binding: FragmentOneSCTwoBinding
    private val binding get() = _binding
    private var puneriTvId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOneSCTwoBinding.inflate(inflater, container, false)
        arguments?.let {
            puneriTvId = it.getString("PUNERI_TV_ID")
        }
        puneriTvId?.let { getPuneriTvVideoData(it) }

        return binding.root
    }

    private fun getPuneriTvVideoData(puneriTvId: String) {
        val retrofitData = RetrofitInstance.apiInterface.getPuneriTvData(puneriTvId)
        retrofitData.enqueue(object : Callback<PuneriTvVideo?> {
            override fun onResponse(
                call: Call<PuneriTvVideo?>,
                response: Response<PuneriTvVideo?>
            ) {
                if (response.isSuccessful) {
                val puneriTvVideoList = response.body()
                if (puneriTvVideoList != null) {
                    val url = puneriTvVideoList.url
                    setupWebView(url)
                } else {
                    Log.e("FragmentOneSCTwo", "No video data available")
                }
            } else {
                    Log.e("FragmentOneSCTwo", "Response unsuccessful: ${response.errorBody()?.string()}")
            }

            }

            override fun onFailure(call: Call<PuneriTvVideo?>, t: Throwable) {
                // Handle failure
                t.printStackTrace()
                Log.e("FragmentOneSCTwo", "API call failed: ${t.message}")
            }
        })
    }
    private fun setupWebView(url: String) {
        binding.videoWebView.apply {
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webViewClient = WebViewClient()
            loadUrl("https://www.youtube.com/watch?v=$url")
        }
    }
}