package it.ac.riqsudev.newsappskotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.FragmentHomeBinding
import it.ac.riqsudev.newsappskotlin.ui.adapter.PageAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    companion object {
        private val TABS_TITLE = intArrayOf(
            R.string.news,
            R.string.business,
            R.string.technology,
            R.string.entertainment,
            R.string.health,
            R.string.sport,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initPageAdapter()

        return  root
    }

    private fun initPageAdapter() {
        val pageAdapter = PageAdapter(this)
        val viewPager : ViewPager2 = binding.viewPager2
        viewPager.adapter = pageAdapter
        val tabs: TabLayout = binding.categoryTabs
        TabLayoutMediator(tabs, viewPager) {tabs, position -> tabs.text = resources.getString(
            TABS_TITLE[position])}.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}