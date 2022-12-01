package it.ac.riqsudev.newsappskotlin.ui.home.category.sports

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
import it.ac.riqsudev.newsappskotlin.databinding.FragmentSportsBinding
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.ui.adapter.NewsAdapter
import it.ac.riqsudev.newsappskotlin.ui.detail.DetailActivity

class SportsFragment : Fragment() {

    private var _binding: FragmentSportsBinding? = null
    private val binding get() = _binding

    private lateinit var sportVM: SportsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        // Inflate the layout for this fragment
        _binding = FragmentSportsBinding.inflate(inflater, container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            rvNewsSport.layoutManager = LinearLayoutManager(context)
            rvNewsSport.setHasFixedSize(true)
        }

        adapter = NewsAdapter()

        sportVM = ViewModelProvider(requireActivity())[SportsViewModel::class.java]
        sportVM.setNews()
        sportVM.getNews().observe(requireActivity()) {
            if (it != null) {
                adapter.setList(it)
                binding?.rvNewsSport?.adapter = adapter
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