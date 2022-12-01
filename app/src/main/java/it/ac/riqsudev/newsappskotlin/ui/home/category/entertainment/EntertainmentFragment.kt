package it.ac.riqsudev.newsappskotlin.ui.home.category.entertainment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import it.ac.riqsudev.newsappskotlin.R
import it.ac.riqsudev.newsappskotlin.databinding.FragmentEntertainmentBinding
import it.ac.riqsudev.newsappskotlin.model.Article
import it.ac.riqsudev.newsappskotlin.ui.adapter.NewsAdapter
import it.ac.riqsudev.newsappskotlin.ui.detail.DetailActivity

class EntertainmentFragment : Fragment() {

    private var _binding: FragmentEntertainmentBinding? = null
    private val binding get() = _binding

    private lateinit var  entertainVM: EntertainmentViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntertainmentBinding.inflate(inflater, container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            rvNewsEntertainment.layoutManager = LinearLayoutManager(context)
            rvNewsEntertainment.setHasFixedSize(true)
        }

        adapter = NewsAdapter()

        entertainVM = ViewModelProvider(requireActivity())[EntertainmentViewModel::class.java]
        entertainVM.setNews()
        entertainVM.getNews().observe(requireActivity()) {
            if (it != null) {
                adapter.setList(it)
                binding?.rvNewsEntertainment?.adapter = adapter
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