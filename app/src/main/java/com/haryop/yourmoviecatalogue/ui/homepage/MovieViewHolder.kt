package com.haryop.yourmoviecatalogue.ui.homepage

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel_Item
import com.haryop.yourmoviecatalogue.databinding.MovieItemLayoutBinding
import com.haryop.yourmoviecatalogue.utils.setImageGlide

class MovieViewHolder(
    private val itemBinding: MovieItemLayoutBinding,
    private val listener: MovieListAdapter.MovieItemListener
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: DetailDataModel) = with(itemBinding) {
        titleTextView.text = "${item.title} (${item.year})"
        subtitleTextView.text = item.type
        itemView.context.setImageGlide(item.poster, itemBinding.root, posterImageView)

        itemView.setOnClickListener { listener.onClickedItem(item.imdbID, item.title) }

    }

}