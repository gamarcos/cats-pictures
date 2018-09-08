package br.com.gabrielmarcos.catspictures.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import br.com.gabrielmarcos.catspictures.R
import br.com.gabrielmarcos.catspictures.service.PicturesService
import br.com.gabrielmarcos.catspictures.view.adapters.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Gabriel Marcos on 05/09/2018
 */
class MainActivity : AppCompatActivity() {

    private var picturesService: PicturesService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        picturesService = PicturesService(this)

        setupTabLayout()
        setupView()
    }

    private fun setupView() {
        viewPager.adapter = PagerAdapter(this.supportFragmentManager, tabLayout.tabCount)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
        })
    }

    private fun setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title_cat)))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_title_cat_hats))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }
}
