package com.bulich.misha.githubtesttask.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.bulich.misha.githubtesttask.R
import com.bulich.misha.githubtesttask.databinding.FragmentHomeBinding
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bulich.misha.githubtesttask.presentation.adapter.HomeAdapter
import com.bulich.misha.githubtesttask.presentation.viewmodels.HomeScreenViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.RuntimeException


class HomeScreenFragment : Fragment() {

    private val viewModel by viewModel<HomeScreenViewModel>()

    private lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeScreen == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                homeAdapter.submitData(it)
            }
        }

        homeAdapter.addLoadStateListener {
            val refreshState = it.source.refresh
            val appendState = it.source.append
            when (refreshState) {
                is LoadState.Error ->
                    if (!viewModel.getInternetStatus()) {
                        errorSnackBar("Проверьте подключние к интернету").show()
                    }else {
                        errorSnackBar(refreshState.error.toString()).show()
                    }

                is LoadState.Loading -> {
                    loadingSnackBar().show()
                }
                is LoadState.NotLoading -> {}
            }
            when (appendState) {
                is LoadState.Error ->
                    if (!viewModel.getInternetStatus()) {
                        errorSnackBar("Проверьте подключние к интернету").show()
                    }else {
                        errorSnackBar(appendState.error.toString()).show()
                    }

                is LoadState.Loading -> {
                    loadingSnackBar().show()
                }
                is LoadState.NotLoading -> {}
            }
        }

    }

    private fun errorSnackBar(message: String): Snackbar {
        return Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
    }

    private fun loadingSnackBar(): Snackbar {
        return Snackbar.make(binding.root, "Загрузка...", Snackbar.LENGTH_LONG)
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.recyclerView.adapter = homeAdapter

        homeAdapter.onItemGitHubRepoClickListener = {
            launchDetailsFragment(it)
        }

    }

    private fun launchDetailsFragment(item: GitHubRepo) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(item))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HomeScreenFragment {
            return HomeScreenFragment()
        }
    }
}