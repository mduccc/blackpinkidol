package com.indieteam.blackpinkidol.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.indieteam.blackpinkidol.R
import com.indieteam.blackpinkidol.process.Profile
import com.indieteam.blackpinkidol.ui.activity.MainActivity

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Profile(activity as MainActivity, this).get()
        (activity as MainActivity).fragmentEvents.onProfileItemsListen(this)
    }

    fun onBack(): Int{
        val count = childFragmentManager.backStackEntryCount
        if(count > 0){
            childFragmentManager.popBackStackImmediate()
        }
        return count
    }

}
