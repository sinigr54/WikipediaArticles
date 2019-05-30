package com.sinigr.wikipediaarticles.modules.main.articles_list.view.adapter

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sinigr.wikipediaarticles.entity.ArticleEntity
import com.sinigr.wikipediaarticles.modules.main.articles_list.view.adapter.delegates.ArticleAdapterDelegate

class ArticlesAdapter(activity: Activity) : ListDelegationAdapter<List<ArticleEntity>>() {

    init {
        delegatesManager.addDelegate(ArticleAdapterDelegate(activity))
    }

    fun setData(articles: List<ArticleEntity>) {
        setItems(articles)
        notifyDataSetChanged()
    }

}