package com.example.tcprojecttwo

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tcprojecttwo.Fragments.FragmentFour
import com.example.tcprojecttwo.Fragments.FragmentThree
import com.example.tcprojecttwo.Fragments.FragmentOne
import com.example.tcprojecttwo.Fragments.FragmentTwo
import com.example.tcprojecttwo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastSelectedItemId: Int = R.id.icon1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            lastSelectedItemId = menuItem.itemId
            when (menuItem.itemId) {
                R.id.icon1 -> {
                    replaceFragments(FragmentOne())
                    true
                }

                R.id.icon2 -> {
                    replaceFragments(FragmentTwo())
                    true
                }

                R.id.icon3 -> {
                    replaceFragments(FragmentThree())
                    true
                }

                R.id.icon4 -> {
                    replaceFragments(FragmentFour())
                    true
                }
                else -> false
            }
        }
                if (savedInstanceState == null) {
                    binding.bottomNav.selectedItemId = R.id.icon1
                }
            }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun replaceFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}
