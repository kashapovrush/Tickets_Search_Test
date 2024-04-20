package com.kashapovrush.geo_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.kashapovrush.common.constants.Constants

class GeoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_geo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResult(
            Constants.NAVIGATION_STATE, bundleOf(
                Constants.STATE to Constants.GEO_STATE)
        )
    }

    companion object {

        fun newInstance() = GeoFragment()
    }
}