package com.cmd.hit.imstandalone.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var itemList = ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return itemList[position]
    }

    override fun getCount(): Int {
        return itemList.size
    }

    fun setList(list : ArrayList<Fragment>) {
        this.itemList = list
        notifyDataSetChanged()
    }
}