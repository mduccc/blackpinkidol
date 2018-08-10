package com.indieteam.blackbinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackbinkidol.R
import com.indieteam.blackbinkidol.process.Mv
import com.indieteam.blackbinkidol.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MvFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Mv().request(activity as MainActivity)
    }
}
