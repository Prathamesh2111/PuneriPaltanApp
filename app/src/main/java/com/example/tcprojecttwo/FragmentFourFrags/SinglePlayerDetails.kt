package com.example.tcprojecttwo.FragmentFourFrags

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.tcprojecttwo.DataClasses.PlayersDataItem
import com.example.tcprojecttwo.RetrofitInstance
import com.example.tcprojecttwo.databinding.FragmentSinglePlayerDetailsBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SinglePlayerDetails : Fragment() {

    private var _binding: FragmentSinglePlayerDetailsBinding? = null
    private val binding get() = _binding!!
    var playerid: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSinglePlayerDetailsBinding.inflate(inflater, container, false)

        playerid = arguments?.getString("key")
        getSingleDetails()

        return binding.root
    }

    private fun getSingleDetails() {
        playerid?.let {
            val retrofitData = RetrofitInstance.apiInterface.getSinglePlayerData(it)

            retrofitData.enqueue(object : Callback<List<PlayersDataItem>?> {
                override fun onResponse(
                    call: Call<List<PlayersDataItem>?>,
                    response: Response<List<PlayersDataItem>?>
                ) {
                    if (!isAdded) return
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null && responseBody.isNotEmpty()) {
                            val player = responseBody[0]
                            binding.nametv.text = player.name
                            binding.dobtv.text = player.date_of_birth
                            binding.positiontv.text = player.position
                            binding.jerseytv.text = player.jersey_no
                            binding.matchesPlayed.text = player.Matches_played
                            binding.totalPointsEarned.text = player.total_ponints_earned
                            binding.mostPointsInAMatch.text = player.most_points_in_a_match
                            binding.notOutPercentage.text = player.not_out_percentage
                            val imageUrl = player.full_image
                            Picasso.get().load(imageUrl).into(binding.imageView3)
                        } else {
                            Log.e("SinglePlayerDetails", "Response body is empty or null")
                        }
                    } else {
                        Log.e("SinglePlayerDetails", "Failed to get response: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<List<PlayersDataItem>?>, t: Throwable) {
                    if (!isAdded) return
                    Toast.makeText(context, "Failed: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        } ?: run {
            Log.e("SinglePlayerDetails", "Player ID is null")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}