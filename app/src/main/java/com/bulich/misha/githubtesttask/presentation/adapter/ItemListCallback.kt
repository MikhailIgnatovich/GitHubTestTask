package com.bulich.misha.githubtesttask.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bulich.misha.githubtesttask.domain.model.GitHubRepo

class ItemListCallback : DiffUtil.ItemCallback<GitHubRepo>() {
    override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
        return oldItem.id == newItem.id && oldItem.nameRepository == newItem.nameRepository
    }
}