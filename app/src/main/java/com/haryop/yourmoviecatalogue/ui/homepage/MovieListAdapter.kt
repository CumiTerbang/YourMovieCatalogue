package com.haryop.yourmoviecatalogue.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haryop.yourmoviecatalogue.data.model.SearchDataModel_Item
import com.haryop.yourmoviecatalogue.databinding.ItemBottomSpaceBinding
import com.haryop.yourmoviecatalogue.databinding.MovieItemLayoutBinding
import com.haryop.yourmoviecatalogue.ui.BottomspaceViewHolder

class MovieListAdapter(private val listener: MovieListAdapter.MovieItemListener) :
    PagingDataAdapter<SearchDataModel_Item, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<SearchDataModel_Item>() {
            override fun areItemsTheSame(
                oldItem: SearchDataModel_Item,
                newItem: SearchDataModel_Item
            ) =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(
                oldItem: SearchDataModel_Item,
                newItem: SearchDataModel_Item
            ) =
                oldItem.imdbID == newItem.imdbID
        }
    }

    interface MovieItemListener {
        fun onClickedItem(imdb_id: String, title: String)
    }

    val ITEM_TYPE_ITEM_LAYOUT = 0
    val ITEM_TYPE_BOTTOMSPACE_LAYOUT = 1
    override fun getItemViewType(position: Int): Int {
        if (super.getItem(position) is SearchDataModel_Item) {
            return ITEM_TYPE_ITEM_LAYOUT
        } else {
            return ITEM_TYPE_BOTTOMSPACE_LAYOUT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_ITEM_LAYOUT -> {
                val binding: MovieItemLayoutBinding =
                    MovieItemLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return MovieViewHolder(binding, listener)
            }
            else -> {
                val binding: ItemBottomSpaceBinding =
                    ItemBottomSpaceBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return BottomspaceViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(itemCount==0){
            return
        }

        when (holder.getItemViewType()) {
            ITEM_TYPE_ITEM_LAYOUT -> {
                var mHolder = holder as MovieViewHolder
                mHolder.bind(super.getItem(position) as SearchDataModel_Item)
            }

            ITEM_TYPE_BOTTOMSPACE_LAYOUT -> {
                //do nothing
            }

        }
    }

}