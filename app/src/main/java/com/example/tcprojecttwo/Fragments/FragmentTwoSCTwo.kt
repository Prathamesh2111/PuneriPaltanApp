package com.example.tcprojecttwo.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tcprojecttwo.Adapters.GalleryShowAdapter
import com.example.tcprojecttwo.DataClasses.GalleryShowItem
import com.example.tcprojecttwo.R
import com.example.tcprojecttwo.RetrofitInstance
import com.example.tcprojecttwo.databinding.FragmentTwoBinding
import com.example.tcprojecttwo.databinding.FragmentTwoSCTwoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentTwoSCTwo : Fragment() {

    private lateinit var _binding: FragmentTwoSCTwoBinding
    private val binding get() = _binding
    private var galleryID: String? = null
    private lateinit var galleryShowAdapter: GalleryShowAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTwoSCTwoBinding.inflate(inflater, container, false)
        arguments?.let {
            galleryID = it.getString("GALLERY_ID")
        }
        galleryID?.let { getGalleryShowData(it) }

        linearLayoutManager = LinearLayoutManager(context)
        binding.galleryshowRv.layoutManager = linearLayoutManager
        galleryShowAdapter = GalleryShowAdapter(emptyList()) // Initialize with an empty list
        binding.galleryshowRv.adapter = galleryShowAdapter
        binding.progressBarFtwoSCTwo.visibility = View.VISIBLE
        return binding.root
    }

    fun getGalleryShowData(galleryID: String){
        val retrofitData = RetrofitInstance.apiInterface.getGalleryData(galleryID)
        retrofitData.enqueue(object : Callback<List<GalleryShowItem>?> {
            override fun onResponse(
                call: Call<List<GalleryShowItem>?>,
                response: Response<List<GalleryShowItem>?>
            ) {
                if (response.isSuccessful) {
                    val galleryShowList = response.body()
                    Log.d("FragmentTwoSCTwo", "Received data: $galleryShowList") // Add this line
                    galleryShowList?.let {
                        galleryShowAdapter.updateData(it)
                    }
                    binding.progressBarFtwoSCTwo.visibility = View.GONE
                } else {
                    Log.e("FragmentTwoSCTwo", "Response not successful: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<GalleryShowItem>?>, t: Throwable) {
                Log.e("FragmentOneSCTwo", "API call failed: ${t.message}")
            }
        })
    }
}