package com.haryop.yourmoviecatalogue.ui.homepage

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.haryop.yourmoviecatalogue.R
import com.haryop.yourmoviecatalogue.databinding.FragmentHomePageBinding
import com.haryop.yourmoviecatalogue.ui.ToolbarListener
import com.haryop.yourmoviecatalogue.utils.BaseFragmentBinding
import com.haryop.yourmoviecatalogue.utils.ConstantsObj
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragmentBinding<FragmentHomePageBinding>(),
    MovieListAdapter.MovieItemListener, SwipeRefreshLayout.OnRefreshListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomePageBinding
        get() = FragmentHomePageBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: MovieListAdapter
    lateinit var viewBinding: FragmentHomePageBinding
    var query: String = ""

    override fun setupView(binding: FragmentHomePageBinding) {
        viewBinding = binding
        setUpRecyclerView()
    }

    fun setUpRecyclerView() = with(viewBinding) {
        adapter = MovieListAdapter(this@HomePageFragment)
        adapter.addLoadStateListener {
            if(it.source.refresh is LoadState.Loading){
                loadingLayout.shimmerView.startShimmer()
                loadingLayout.loadingContainer.visibility = View.VISIBLE
                movieRecyclerView.visibility = View.GONE
            }else{
                loadingLayout.shimmerView.stopShimmer()
                loadingLayout.loadingContainer.visibility = View.GONE
                movieRecyclerView.visibility = View.VISIBLE
            }

            if(it.source.refresh is LoadState.Error){
                welcome.visibility = View.VISIBLE
                welcome.text = "Movie not found"
                movieRecyclerView.visibility = View.GONE
            }
        }

        val layManager = LinearLayoutManager(requireContext())
        movieRecyclerView.layoutManager = layManager
        movieRecyclerView.adapter = adapter

        swipeContainer.setOnRefreshListener(this@HomePageFragment)
    }

    private fun onGetSourcesObserver(query: String) = with(viewBinding) {
        viewModel.fetchSearchLiveData(query).observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                swipeContainer.isRefreshing = false
                adapter.submitData(it)
            }
        })
    }

    fun onSearchMovie(_query: String) = with(viewBinding) {
        welcome.visibility = View.GONE
        query = _query
        requireActivity().viewModelStore.clear()
        onGetSourcesObserver(_query)
    }

    override fun onResume() {
        (activity as ToolbarListener).onUpdateToolbar(ConstantsObj.HOME_PAGE, "")
        if (!query.equals("")) {
            viewBinding.welcome.visibility = View.GONE
            onGetSourcesObserver(query)
        }

        super.onResume()
    }

    override fun onClickedItem(imdb_id: String, title: String) {
        openDetail(imdb_id, title)
    }

    fun openDetail(imdb_id: String, title: String) {
        findNavController().navigate(
            R.id.action_home_to_detail, bundleOf("imdb_id" to imdb_id)
        )

        (activity as ToolbarListener).onUpdateToolbar(ConstantsObj.DETAIL_PAGE, title)
    }

    override fun onRefresh() {
        adapter.refresh()
    }

}