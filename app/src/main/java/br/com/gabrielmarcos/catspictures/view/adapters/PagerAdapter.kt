package br.com.gabrielmarcos.catspictures.view.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.gabrielmarcos.catspictures.view.fragments.CatsFragment
import br.com.gabrielmarcos.catspictures.view.fragments.HatsFragment

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class PagerAdapter(fm: FragmentManager, val tabs: Int): FragmentStatePagerAdapter(fm) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf(CatsFragment(), HatsFragment())

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return tabs
    }

}