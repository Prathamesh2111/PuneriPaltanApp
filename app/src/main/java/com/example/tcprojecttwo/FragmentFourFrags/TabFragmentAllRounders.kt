package com.example.tcprojecttwo.FragmentFourFrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tcprojecttwo.Adapters.PlayersAdapter
import com.example.tcprojecttwo.DataClasses.PlayersItem
import com.example.tcprojecttwo.R
import com.example.tcprojecttwo.RetrofitInstance
import com.example.tcprojecttwo.databinding.FragmentTabAllRoundersBinding
import com.example.tcprojecttwo.databinding.FragmentTabDefendersBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabFragmentAllRounders : Fragment() {

    lateinit var playersAdapter: PlayersAdapter
    private var _binding: FragmentTabAllRoundersBinding? = null
    private val binding get() = _binding!!
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTabAllRoundersBinding.inflate(inflater, container, false)

        binding.allroundersRv.setHasFixedSize(false)
        linearLayoutManager = LinearLayoutManager(context)
        binding.allroundersRv.layoutManager = linearLayoutManager
        binding.progressBarFthree.visibility = View.VISIBLE
        getPlayersData()
        return binding.root
    }

    fun getPlayersData() {
        val retrofitData = RetrofitInstance.apiInterface.getPlayers(3.toString())

        retrofitData.enqueue(object : Callback<List<PlayersItem>?> {
            override fun onResponse(call: Call<List<PlayersItem>?>, response: Response<List<PlayersItem>?>) {
                if (!isAdded) return
                if (response.isSuccessful) {
                    val responseBody = response.body()!!

                    playersAdapter = PlayersAdapter(context?: return, responseBody) {
                        navigateToFragmentSinglePlayerDetails(it.id)
                    }
                    binding.progressBarFthree.visibility = View.GONE
                    binding.allroundersRv.adapter = playersAdapter
                }
                else {
                    Log.e("TabFragmentRaiders", "Failed to get response or response body is null")
                    Log.e("TabFragmentRaiders", "Response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<PlayersItem>?>, t: Throwable) {
                if (!isAdded) return
                Log.e("MainActivity", "onFailure: " + t.message)
                Toast.makeText(context, "Failed" + t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun navigateToFragmentSinglePlayerDetails(playerid: String) {
        val singlePlayerDetails = SinglePlayerDetails().apply {
            arguments = Bundle().apply {
                putString("key", playerid)
            }
        }
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_container, singlePlayerDetails)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}