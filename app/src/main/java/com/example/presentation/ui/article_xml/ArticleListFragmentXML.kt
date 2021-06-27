package com.example.presentation.ui.article_xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apadter.ArticleAdapter
import com.example.machinetest.databinding.ArticlesFragmentBinding
import com.example.presentation.ui.article_compose.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class ArticleListFragmentXML : Fragment() {

    //XMl Template

    private val viewModel: ArticleViewModel by viewModels()
    private var _binding: ArticlesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArticlesFragmentBinding.inflate(inflater, container, false)
        binding.swiperefresh.setOnRefreshListener {
            populateData()
            binding.swiperefresh.isRefreshing = false
        }
        populateData()
        return binding.root
    }

    private fun populateData() {
        binding.progress.visibility = View.VISIBLE
        lifecycleScope.launchWhenResumed {
            viewModel.listRxEvent()
            withContext(Dispatchers.Main) {
                binding.progress.visibility = View.GONE
                val adapter = ArticleAdapter(viewModel.listRx);
                binding.rvArticles.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rvArticles.adapter = adapter
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


