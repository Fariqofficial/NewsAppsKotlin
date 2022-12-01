package it.ac.riqsudev.newsappskotlin.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.FragmentSearchBinding
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.ui.adapter.NewsAdapter
import it.ac.riqsudev.newsappskotlin.ui.detail.DetailActivity

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchVM: SearchViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyState(true)
        searchNews()

        binding.apply {
            rvNewsSearch.layoutManager = LinearLayoutManager(context)
            rvNewsSearch.setHasFixedSize(true)
        }

        adapter = NewsAdapter()

        searchVM = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        searchVM.getNews().observe(requireActivity()) {
            if (it != null) {
                emptyState(false)
                adapter.setList(it)
                showProgress(false)
                binding.rvNewsSearch.adapter = adapter
                adapter.notifyDataSetChanged()

                //onClick
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

    private fun searchNews() {
        binding.apply {
            searchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        if (it.isNotEmpty()) {
                            searchVM.setNews(query)
                            showProgress(true)
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean = true
            })
        }
    }

    private fun showProgress(state: Boolean) {
        if (state) {
            binding.progressBarLinear.visibility = View.VISIBLE
        }
        else {
            binding.progressBarLinear.visibility = View.GONE
        }

    }

    private fun emptyState(state: Boolean) {
        if (state) {
            binding.searchState.root.visibility = View.VISIBLE
        }
        else {
            binding.searchState.root.visibility = View.GONE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}