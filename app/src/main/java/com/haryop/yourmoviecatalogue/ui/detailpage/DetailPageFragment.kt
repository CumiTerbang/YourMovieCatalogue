package com.haryop.yourmoviecatalogue.ui.detailpage

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.fragment.app.viewModels
import com.haryop.yourmoviecatalogue.R
import com.haryop.yourmoviecatalogue.data.Resource
import com.haryop.yourmoviecatalogue.data.model.DetailDataModel
import com.haryop.yourmoviecatalogue.databinding.FragmentDetailPageBinding
import com.haryop.yourmoviecatalogue.utils.BaseFragmentBinding
import com.haryop.yourmoviecatalogue.utils.setImageGlide
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.full.memberProperties

@AndroidEntryPoint
class DetailPageFragment : BaseFragmentBinding<FragmentDetailPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailPageBinding
        get() = FragmentDetailPageBinding::inflate

    private val viewModel: DetailViewModel by viewModels()
    var imdb_id: String = ""
    lateinit var viewBinding: FragmentDetailPageBinding

    override fun setupView(binding: FragmentDetailPageBinding) {
        viewBinding = binding

        arguments?.getString("imdb_id")?.let {
            imdb_id = it
            viewModel.start(imdb_id)
            onGetDetailObserver()
        }

    }

    var isSetUpContent = false
    private fun onGetDetailObserver() = with(viewBinding) {
        viewModel.getDetailData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {

                    if (it.data != null && !isSetUpContent) {
                        setUpContent(it.data)
                    }

//                  }else {
//                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                    }

                }
                Resource.Status.ERROR -> {
                    //shimmering stop here
                    loadingLayout.shimmerView.stopShimmer()
                    loadingLayout.loadingContainer.visibility = View.GONE
                    contentLayout.visibility = View.GONE

                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    //shimmering start here
                    loadingLayout.shimmerView.startShimmer()
                    loadingLayout.loadingContainer.visibility = View.VISIBLE
                    contentLayout.visibility = View.GONE

                }
            }

        })
    }

    fun setUpContent(data: DetailDataModel) = with(viewBinding) {
        //shimmering stop here
        loadingLayout.shimmerView.stopShimmer()
        loadingLayout.loadingContainer.visibility = View.GONE
        contentLayout.visibility = View.VISIBLE

        isSetUpContent = true

        context?.setImageGlide(data.poster, viewBinding.root, posterImageView)
        imageContainer.setOnClickListener { openDetailPoster(data.poster) }

        titleTextView.text = "${data.title} (${data.year})"
        subtitleTextView.text = data.type

        detailContentLinearLayout.addView(getDetailTextView("<b>Ratings (${data.ratings.size}):</b>"))
        var ratings_content = "<ul>"
        data.ratings.forEach {
           ratings_content = ratings_content + "<li>source: ${it.source}<br>value: ${it.value}</li>"
        }
        ratings_content = ratings_content + "</ul>"
        detailContentLinearLayout.addView(getDetailTextView(ratings_content))

        for (_data in DetailDataModel::class.memberProperties) {
            if (_data.name.equals("poster") ||
                _data.name.equals("title") ||
                _data.name.equals("year") ||
                _data.name.equals("type") ||
                _data.name.equals("ratings")
            ) {
                return
            }

            if (_data.get(data) is String) {
                val data_name: String =
                    _data.name.substring(0, 1).toUpperCase() + _data.name.substring(1)
                detailContentLinearLayout.addView(
                    getDetailTextView("<b>${data_name}:</b> ${_data.get(data)}")
                )
            }
        }


    }

    fun getDetailTextView(detail_data: String): TextView {
        val tv_dynamic = TextView(requireContext())
        tv_dynamic.textSize = 20f
        tv_dynamic.text = Html.fromHtml(detail_data)
        tv_dynamic.setPadding((resources.getDimension(R.dimen.padding_8).toInt()))

        return tv_dynamic
    }

    fun openDetailPoster(poster_url: String) {
        var intent = Intent(requireContext(), DetailPosterActivity::class.java)
        intent.putExtra("poster_url", poster_url)
        startActivity(intent)
    }

}