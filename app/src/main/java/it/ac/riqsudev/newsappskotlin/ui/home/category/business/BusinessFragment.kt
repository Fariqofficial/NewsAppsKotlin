package it.ac.riqsudev.newsappskotlin.ui.home.category.business

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
import it.ac.riqsudev.newsappskotlin.databinding.FragmentBusinessBinding
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.ui.adapter.NewsAdapter
import it.ac.riqsudev.newsappskotlin.ui.detail.DetailActivity

class BusinessFragment : Fragment() {

    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding

    private lateinit var  businessVM: BusinessViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            rvNewsBusiness.layoutManager= LinearLayoutManager(context)
            rvNewsBusiness.setHasFixedSize(true)
        }

        adapter = NewsAdapter()

        businessVM = ViewModelProvider(requireActivity())[BusinessViewModel::class.java]
        businessVM.setNews()
        businessVM.getNews().observe(requireActivity()) {
            if (it != null) {
                adapter.setList(it)
                binding?.rvNewsBusiness?.adapter = adapter
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
}