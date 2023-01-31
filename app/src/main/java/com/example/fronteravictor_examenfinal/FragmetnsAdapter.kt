package com.example.fronteravictor_examenfinal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmetnsAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    companion object {
        private const val NUM_PAG = 3
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0)
            return Fragment1()
        else
            if(position == 1)
                return Fragment2()
            else
                return Fragment3()
    }

    override fun getItemCount(): Int {
        return NUM_PAG
    }
}