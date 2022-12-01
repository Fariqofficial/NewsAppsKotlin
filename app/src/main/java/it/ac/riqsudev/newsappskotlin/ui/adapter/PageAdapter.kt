package it.ac.riqsudev.newsappskotlin.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import it.ac.riqsudev.newsappskotlin.ui.home.HomeFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.business.BusinessFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.entertainment.EntertainmentFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.health.HealthFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.news.NewsFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.sports.SportsFragment
import it.ac.riqsudev.newsappskotlin.ui.home.category.technology.TechnologyFragment

class PageAdapter(fragment: HomeFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = NewsFragment()
            1 -> fragment = BusinessFragment()
            2 -> fragment = TechnologyFragment()
            3 -> fragment = EntertainmentFragment()
            4 -> fragment = HealthFragment()
            5 -> fragment = SportsFragment()
        }
        return  fragment as Fragment
    }
}