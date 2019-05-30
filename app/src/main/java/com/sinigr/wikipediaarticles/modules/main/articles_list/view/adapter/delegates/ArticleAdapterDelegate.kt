package com.sinigr.wikipediaarticles.modules.main.articles_list.view.adapter.delegates

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.sinigr.wikipediaarticles.R
import com.sinigr.wikipediaarticles.entity.ArticleEntity

class ArticleAdapterDelegate(
    private val activity: Activity
) : AbsListItemAdapterDelegate<ArticleEntity, ArticleEntity, ArticleAdapterDelegate.Holder>() {

    private val layoutInflater: LayoutInflater = activity.layoutInflater

    override fun isForViewType(item: ArticleEntity, items: MutableList<ArticleEntity>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(layoutInflater.inflate(R.layout.item_article, parent, false))
    }

    override fun onBindViewHolder(item: ArticleEntity, holder: Holder, payloads: MutableList<Any>) {
        with(holder) {
            titleTextView.text = item.title

            val imagesCount = item.imagesCount
            imagesCountTextView.text =
                activity.resources.getQuantityString(R.plurals.images_count, imagesCount, imagesCount)
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val imagesCountTextView: TextView = itemView.findViewById(R.id.imageCountTextView)
    }

}