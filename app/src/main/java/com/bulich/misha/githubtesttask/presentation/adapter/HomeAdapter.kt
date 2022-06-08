package com.bulich.misha.githubtesttask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bulich.misha.githubtesttask.databinding.ItemGitRepositoryBinding
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo
import com.bumptech.glide.Glide

class HomeAdapter : PagingDataAdapter<GitHubRepo, HomeAdapter.HomeViewHolder>(ItemListCallback()) {

    var onItemGitHubRepoClickListener: ((GitHubRepo) -> Unit)? = null

    class HomeViewHolder(val binding: ItemGitRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemGitRepositoryBinding.inflate(
            layout,
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        binding.root.setOnClickListener {
            onItemGitHubRepoClickListener?.invoke(item!!)
        }
        binding.loginTv.text = item?.ownerModel?.ownerLogin
        binding.nameRepositoryTv.text = item?.nameRepository
        Glide.with(binding.avatarIv)
            .load(item?.ownerModel?.ownerAvatar)
            .circleCrop()
            .into(binding.avatarIv)
    }
}