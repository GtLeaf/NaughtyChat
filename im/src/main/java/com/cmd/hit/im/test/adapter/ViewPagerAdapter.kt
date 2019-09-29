package com.cmd.hit.im.test.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return list.size
    }

    fun setList(list : ArrayList<Fragment>) {
        this.list = list
        notifyDataSetChanged()
    }
}