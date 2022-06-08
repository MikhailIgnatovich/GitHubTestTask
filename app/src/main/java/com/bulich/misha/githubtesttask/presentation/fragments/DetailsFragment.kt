package com.bulich.misha.githubtesttask.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bulich.misha.githubtesttask.databinding.FragmentDetailsBinding
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bulich.misha.githubtesttask.presentation.viewmodels.DetailsViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class DetailsFragment : Fragment() {

    private lateinit var itemGitHubRepo: GitHubRepo

    private val viewModel by viewModel<DetailsViewModel>()

    private lateinit var url: String


    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<GitHubRepo>(ITEM_REPOSITORY)?.let {
            itemGitHubRepo = it
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        url = parseUrl(itemGitHubRepo.commitsUrl)

        viewModel.getCommit(url)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.commitData.observe(viewLifecycleOwner) {
            val shaAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it.shaList)
            with(binding) {
                messageDataTv.text = it.message
                authorDataTv.text = it.authorName
                dateDataTv.text = parseDate(it.date)
                shaListView.adapter = shaAdapter

            }
        }

        binding.backIv.setOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) {
            if (!it) {
                thereIsNoInternet()
                errorFromServer()
                noError()
            }
        }

        with(binding) {
            Glide.with(ownerAvatarIv)
                .load(itemGitHubRepo.ownerModel.ownerAvatar)
                .circleCrop()
                .into(ownerAvatarIv)

            ownerLoginTv.text = itemGitHubRepo.ownerModel.ownerLogin
            ownerRepositoryTv.text = itemGitHubRepo.nameRepository
        }
    }

    private fun parseUrl(url: String): String {
        return url.removePrefix("https://api.github.com/")
    }

    private fun errorSnackBar(error: String): Snackbar {
        return Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG)
    }

    private fun thereIsNoInternet() {
        if (!viewModel.internetStatus && viewModel.errorServer.isEmpty()) {
            errorSnackBar(THERE_IS_NO_INTERNET).show()
            binding.progressLoading.visibility = View.GONE
            binding.detailsSv.visibility = View.GONE
        }
    }

    private fun errorFromServer() {
        if (viewModel.internetStatus && viewModel.errorServer.isNotEmpty()) {
            errorSnackBar(viewModel.errorServer).show()
            binding.progressLoading.visibility = View.GONE
            binding.detailsSv.visibility = View.GONE
        }
    }

    private fun noError() {
        if (viewModel.internetStatus && viewModel.errorServer.isEmpty()) {
            binding.progressLoading.visibility = View.GONE
            binding.detailsSv.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }


    private fun parseDate(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd MM yyyy")
        return formatter.format(parser.parse(date)!!)
    }

    companion object {

        private const val THERE_IS_NO_INTERNET = "Отсутствует интернет..."
        private const val ITEM_REPOSITORY = "item_repository"

        fun newInstance(repository: GitHubRepo) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ITEM_REPOSITORY, repository)
                }
            }
    }
}