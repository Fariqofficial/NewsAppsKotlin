package it.ac.riqsudev.newsappskotlin.ui.home.category.health

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.FragmentHealthBinding
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.ui.adapter.NewsAdapter
import it.ac.riqsudev.newsappskotlin.ui.detail.DetailActivity

class HealthFragment : Fragment() {

    private var _binding: FragmentHealthBinding? = null
    private val binding get() = _binding

    private lateinit var healthViewModel: HealthViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentHealthBinding.inflate(inflater, container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            rvNewsHealth.layoutManager = LinearLayoutManager(context)
            rvNewsHealth.setHasFixedSize(true)
        }

        adapter = NewsAdapter()

        healthViewModel = ViewModelProvider(requireActivity())[HealthViewModel::class.java]
        healthViewModel.setNews()
        healthViewModel.getNews().observe(requireActivity()) {
            if (it != null) {
                adapter.setList(it)
                binding?.rvNewsHealth?.adapter = adapter
                adapter.notifyDataSetChanged()
                //onclick item
                adapter.setOnItemClickCallback(object : NewsAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Article) {
                        Intent(context, DetailActivity::class.java).also {
                            it.putExtra(DetailActivity.DETAIL_NEWS, data)
                            startActivity(it)
                        }
                    }
                })
            }
        }

    }
}