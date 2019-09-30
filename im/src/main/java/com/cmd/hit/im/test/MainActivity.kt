package com.cmd.hit.im.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.cmd.hit.im.R
import com.cmd.hit.im.test.adapter.ViewPagerAdapter
import com.cmd.hit.im.ui.fragment.ChatMessageFragment
import com.cmd.hit.im.ui.fragment.dummy.DummyContent
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.im_activity_main2.*

class MainActivity : AppCompatActivity(), ChatMessageFragment.OnListFragmentInteractionListener {

    private var menuItem : MenuItem? = null
    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.im_activity_main2)
        initView()
        initListener()
    }

    private fun initView() {
        im_vp_main.adapter = viewPagerAdapter
        viewPagerAdapter.setList(getFragmentList())
    }

    private fun initListener() {
        im_bnv_main.setOnNavigationItemSelectedListener{
//            viewPagerAdapter.setList(getFragmentList())
            menuItem = it
            when (menuItem?.itemId) {
                R.id.bottom_navigation_plaza -> {
                    im_vp_main.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.bottom_navigation_mssage -> {
                    im_vp_main.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {}
            }
            return@setOnNavigationItemSelectedListener false
        }

        im_vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (null == menuItem) {
                    im_bnv_main.menu.getItem(0).isChecked = false
                } else {
                    menuItem?.isChecked = false
                }
                //侧滑时处理
                menuItem = im_bnv_main.menu.getItem(position)
                menuItem?.isChecked = true
            }

            override fun onPageSelected(position: Int) {}
        })
    }

    private fun getFragmentList() : ArrayList<Fragment> {
        return ArrayList<Fragment>().apply {
            this.add(ChatMessageFragment.newInstance(1))
            this.add(ChatMessageFragment.newInstance(2))
        }
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

    }
}
