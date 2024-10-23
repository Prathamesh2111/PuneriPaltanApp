package com.example.tcprojecttwo.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tcprojecttwo.Adapters.FixturesAdapter
import com.example.tcprojecttwo.Adapters.PuneritvAdapter
import com.example.tcprojecttwo.DataClasses.Fixture
import com.example.tcprojecttwo.DataClasses.PuneriTv
import com.example.tcprojecttwo.R
import com.example.tcprojecttwo.databinding.FragmentOneBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentOne : Fragment() {
    private lateinit var puneriTvAdapter: PuneritvAdapter
    private  var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Puneri TV Code
        val puneriTvData = listOf(
            PuneriTv(
                "1",
                "24 March 2023",
                "SWOS Analysis Of Maha Derby | Sunil Taneja | Prokabaddi Season 10",
                R.drawable.sunil_taneja
            ),
            PuneriTv(
                "2",
                "27 March 2023",
                "Aslam Inamdar Post Match Press Conference | PUNvsJ...",
                R.drawable.aslam_inamdar
            ),
            PuneriTv("3", "04 April 2023", "Aslam's View On Nabibakhsh!", R.drawable.nabibaksh)
        )
        puneriTvAdapter = PuneritvAdapter(puneriTvData) { selectedItem ->
            val fragmentOneSCTwo = FragmentOneSCTwo()
            val bundle = Bundle().apply {
                putString("PUNERI_TV_ID", selectedItem.id)
            }
            fragmentOneSCTwo.arguments = bundle
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragmentOneSCTwo)
                .addToBackStack(null)
                .commit()
        }
        binding.puneriTVRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = puneriTvAdapter
        }


        //Puneri Fixers Code
        val fixtures = listOf(
            Fixture(
                R.drawable.patna_pirates_logo,
                R.drawable.dabang_delhi_fixers,
                "08 Apr 2023 19:30 IST"
            ),
            Fixture(
                R.drawable.jaipur_pink_panthers_fixers,
                R.drawable.puneri_paltan_fixers,
                "10 Apr 2023 18:00 IST"
            ),
            Fixture(
                R.drawable.up_yoddhas_fixers,
                R.drawable.patna_pirates_logo,
                "11 Apr 2023 18:00 IST"
            ),
        )
        val fixtureAdapter = FixturesAdapter(
            fixtures,
            onPrevClick = { position ->
                if (position > 0) {
                    binding.viewPager.currentItem = position - 1
                }
            },
            onNextClick = { position ->
                if (position < fixtures.size - 1) {
                    binding.viewPager.currentItem = position + 1
                }
            }
        )
        binding.viewPager.adapter = fixtureAdapter

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clear binding reference to avoid memory leaks
    }
}