package com.kashapovrush.ticketssearchtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.kashapovrush.common.constants.Constants
import com.kashapovrush.country_selected_screen.ui.CountrySelectedFragment
import com.kashapovrush.geo_screen.GeoFragment
import com.kashapovrush.hard_screen.HardFragment
import com.kashapovrush.hot_ticktes_screen.HotTicketsFragment
import com.kashapovrush.hotels_screen.HotelsFragment
import com.kashapovrush.main_screen.ui.MainFragment
import com.kashapovrush.profile_screen.ProfileFragment
import com.kashapovrush.subscribe_screen.SubscribeFragment
import com.kashapovrush.tickets_screen.ui.TicketsFragment
import com.kashapovrush.ticketssearchtest.databinding.ActivityMainBinding
import com.kashapovrush.weekend_screen.WeekendFragment

class MainActivity : AppCompatActivity(),  MainFragment.ClickListenerFromMain, CountrySelectedFragment.ClickListenerFromCountrySelected {

    private var stateScreen = Constants.MAIN_STATE
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.popBackStack()
            supportFragmentManager.commit {
                replace(R.id.container_for_fragments, MainFragment.newInstance())
            }
        }

        binding.aviaTab.setOnClickListener {
            clearEnabledBottomBar()
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container_for_fragments, MainFragment.newInstance())
            }
        }

        binding.hotelsTab.setOnClickListener {
            clearEnabledBottomBar()
            supportFragmentManager.popBackStack()
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container_for_fragments, HotelsFragment.newInstance())
            }
        }

        binding.geoTab.setOnClickListener {
            clearEnabledBottomBar()
            supportFragmentManager.popBackStack()
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container_for_fragments, GeoFragment.newInstance())
            }
        }

        binding.subscribeTab.setOnClickListener {
            clearEnabledBottomBar()
            supportFragmentManager.popBackStack()
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container_for_fragments, SubscribeFragment.newInstance())
            }
        }

        binding.profileTab.setOnClickListener {
            clearEnabledBottomBar()
            binding.profileTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
            supportFragmentManager.popBackStack()
            supportFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container_for_fragments, ProfileFragment.newInstance())
            }
        }
    }


    override fun onResume() {
        super.onResume()

        supportFragmentManager.setFragmentResultListener(Constants.NAVIGATION_STATE, this) { _, bundle ->
            stateScreen = bundle.getInt(Constants.STATE)

            when(stateScreen) {
                Constants.MAIN_STATE -> {
                    clearEnabledBottomBar()
                    binding.aviaTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
                    binding.aviaIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.blue))
                }

                Constants.GEO_STATE -> {
                    clearEnabledBottomBar()
                    binding.geoTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
                    binding.geoIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.blue))
                }

                Constants.SUBSCRIBE_STATE -> {
                    clearEnabledBottomBar()
                    binding.subscribeTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
                    binding.subscribeIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.blue))
                }

                Constants.PROFILE_STATE -> {
                    clearEnabledBottomBar()
                    binding.profileTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
                    binding.profileIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.blue))
                }

                Constants.HOTELS_STATE -> {
                    clearEnabledBottomBar()
                    binding.hotelsTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.blue))
                    binding.hotelsIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.blue))
                }
            }
        }
    }


    private fun clearEnabledBottomBar() {
        with(binding) {
            aviaTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            hotelsTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            geoTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            subscribeTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            profileTitle.setTextColor(resources.getColor(com.kashapovrush.palette.R.color.grey6))

            aviaIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            hotelsIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            geoIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            subscribeIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.grey6))
            profileIcon.setColorFilter(resources.getColor(com.kashapovrush.palette.R.color.grey6))
        }
    }

    override fun clickListenerToHard() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container_for_fragments, HardFragment.newInstance())
        }
    }

    override fun clickListenerToHotTickets() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container_for_fragments, HotTicketsFragment.newInstance())
        }
    }

    override fun clickListenerToWeekend() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container_for_fragments, WeekendFragment.newInstance())
        }
    }

    override fun clickListenerToCountrySelected(value: String) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container_for_fragments, CountrySelectedFragment.newInstance(value))
        }
    }

    override fun clickListenerToTickets(route: String, date: String) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.container_for_fragments, TicketsFragment.newInstance(route, date))
        }
    }
}