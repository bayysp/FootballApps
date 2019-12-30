package com.example.kadesubmisidua.adapter.pageradapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerMatchAdapter(fragmentManager: FragmentManager, idLeague: String) :
    FragmentPagerAdapter(fragmentManager) {

    private val fragmentTags : MutableMap<Int, String>
    private val fragmentList = ArrayList<Fragment>()
    private var idLeague = ""

    init {
        this.fragmentTags = HashMap()
        this.idLeague = idLeague
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

    override fun instantiateItem(container: View, position: Int): Any {
        val obj = super.instantiateItem(container, position)
        if (obj is Fragment){
            val tag = obj.tag
            fragmentTags[position] = tag!!
        }

        return obj
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Previous Match"
            1 -> "Next Match"
            2 -> "Classement"
            else -> " "
        }
    }


}